package com.ang.frontui.service;


import com.ang.frontui.bean.QueryInfo;
import com.ang.frontui.bean.QueryItem;
import com.hubspot.jinjava.Jinjava;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryService {

    private static final String CHILDS = "childs";
    private static final String DIMENSION = "dimension";
    private static final String MEASURE = "measure";
    private static final String TABLE_NAME = "tableName";

    String single = "select\n" +
            "  {% for item in " + DIMENSION + " -%}\n" +
            "    {{item.value}} ,\n" +
            "  {%- endfor %}\n" +
            "  {% for item in " + MEASURE + " -%}\n" +
            "    sum({{item.value}}) {% if !loop.last %},{% endif %}\n" +
            "  {%- endfor %}\n" +
            " from(  {% for child in childs -%}    {{child}}    {% if !loop.last %}union {% endif %}      {%- endfor %}  )  as st" +
            "  group by\n" +
            "  {% for item in " + DIMENSION + " -%}\n" +
            "    st.{{item.value}}{% if !loop.last %},{% endif %}\n" +
            "  {%- endfor %}\n" +
            "{%- endif %}";

    String child = "select\n" +
            "  {% for item in " + DIMENSION + " -%}\n" +
            "    {{item.value}} ,\n" +
            "  {%- endfor %}\n" +
            "  {% for item in " + MEASURE + " -%}\n" +
            "    {{item.value}} {{item.name}}{% if !loop.last %},{% endif %}\n" +
            "  {%- endfor %}\n" +
            " from {{" + TABLE_NAME + "}}\n" +
            " where 1=1\n" +
            " and cancledclass=0\n" +
            "  {% if where -%}\n" +
            "    and\n" +
            "  {%- endif %}\n" +
            "  {{where}}\n" +
            " {% if "+DIMENSION+"|length > 0 -%}\n" +
            " group by\n" +
            "  {% for item in " + DIMENSION + " -%}\n" +
            "    {{item.value}}{% if !loop.last %},{% endif %}\n" +
            "  {%- endfor %}\n" +
            "{%- endif %}";

    public String genSql(QueryInfo queryInfo) {
        List<QueryItem> measure = queryInfo.getMeasure();
        List<String> childs = new ArrayList<>(measure.size());

        for (int i = 0; i < measure.size(); i++) {
            QueryItem queryItem = measure.get(i);
            ArrayList<QueryItem> sepmeasure = new ArrayList<>(measure.size());
            for(int j=0;j< measure.size();j++){
                QueryItem queryItem1 = new QueryItem();
                queryItem1.setName(measure.get(j).getValue());
                if(j==i){
                    queryItem1.setValue(measure.get(j).getValue());
                }else {
                    queryItem1.setValue(0+"");
                }
                sepmeasure.add(queryItem1);
            }
            HashMap<String, Object> param = new HashMap<>();
            param.put(MEASURE, sepmeasure);
            param.put(DIMENSION, queryInfo.getDimension());
            param.put(TABLE_NAME, "dim_table");
            childs.add( replaceSql(child, param));
        }

        HashMap<String, Object> outparam = new HashMap<>();
        outparam.put(DIMENSION,queryInfo.getDimension());
        outparam.put(MEASURE,queryInfo.getMeasure());
        outparam.put(CHILDS,childs);
        String sql = replaceSql(single, outparam);
        System.out.println(sql);
        return sql;
    }


    public String replaceSql(String selTemp, Map<String, Object> bindings) {
        return new Jinjava().render(selTemp, bindings);
    }
}
