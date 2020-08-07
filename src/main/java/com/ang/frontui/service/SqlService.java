package com.ang.frontui.service;

import com.hubspot.jinjava.Jinjava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SqlService {

    public final static String PROPS  ="props";
    public final static String WEIDU  ="weidu";

    public static String genSql(String selTemp,Map<String,Object> bindings){
        return new Jinjava().render(selTemp, bindings );
    }

    public static void main(String[] args) {
        String sigle = " select  {% for item in alls -%}      {{item}}{% if !loop.last %},{% endif %}  {%- endfor %}  from(      {% for child in childs -%}    {{child}}    {% if !loop.last %}union {% endif %}      {%- endfor %}  )  as st group by   {% if weidu|length > 0 -%}      {% for item in weidu -%}        {{item}}{% if !loop.last %},{% endif %}      {%- endfor %}    {%- endif %}";

        String child= "select\n" +
                "  {% for item in props -%}\n" +
                "    {{item}}{% if !loop.last %},{% endif %}\n" +
                "  {%- endfor %}\n" +
                "from {{tableName}}\n" +
                "where 1=1\n" +
                " and cancledclass=0\n" +
                "  {% if where -%}\n" +
                "    and\n" +
                "  {%- endif %}\n" +
                "  {{where}}\n" +
                "{% if props|length > 0 -%}\n" +
                "group by\n" +
                "  {% for item in weidu -%}\n" +
                "    {{item}}{% if !loop.last %},{% endif %}\n" +
                "  {%- endfor %}\n" +
                "{%- endif %}" ;
        String myself = "CASE \n" +
                "WHEN MANAGEMENTNAME = '少儿部' THEN  GRADEINNERNAME\n" +
                "WHEN MANAGEMENTNAME = '国外部' THEN  PRODUCTSYSTEMNAME\n" +
                "WHEN MANAGEMENTNAME = '国内部' THEN  PRODUCTLEVELNAME\n" +
                "END as mc";

        String myZhi = "SUM(\n" +
                "CASE \n" +
                "WHEN  MANAGEMENTNAME = '少儿部' THEN (CASE WHEN PRODUCTSYSTEMNAME <>'计费体系'  AND PRODUCTLEVELNAME <>'专项体系'  THEN metric_cash ELSE 0 END)\n" +
                "\n" +
                " WHEN MANAGEMENTNAME = '国外部'  THEN ( CASE WHEN PRODUCTLEVELNAME <>'常规体系' THEN metric_cash ELSE 0 END)\n" +
                "WHEN MANAGEMENTNAME = '国内部'  THEN (CASE WHEN PRODUCTLEVELNAME like 'zy' THEN metric_cash ELSE 0 END)\n" +
                "ELSE metric_cash \n" +
                "END ) AS   income2020";

        HashMap<String, Object> param = new HashMap<>();
        param.put("tableName","DWS_INCOME_REG_A");
        ArrayList<String> prop = new ArrayList<>();
        prop.add("MANAGEMENTNAME");
        prop.add(myself);

        ArrayList<String> weidu = new ArrayList<>();
        weidu.add("MANAGEMENTNAME");
        weidu.add("mc");

        prop.add("sum(metric_cash)  sum_metric_cash");
        prop.add("0 income2020");

        param.put(PROPS,prop);
        param.put(WEIDU,weidu);

        String u1 = genSql(child, param);
//        System.out.println(u1);

        HashMap<String, Object> param2 = new HashMap<>();
        param2.put("tableName","DWS_INCOME_REG_A");
        ArrayList<String> prop2 = new ArrayList<>();
        prop2.add("MANAGEMENTNAME");
        prop2.add(myself);
        prop2.add("0 sum_metric_cash");
        prop2.add(myZhi);
        param2.put(PROPS,prop2);
        param2.put(WEIDU,weidu);

        String u2 = genSql(child, param2);
//        System.out.println(u2);

        Map<String, Object> sparam = new HashMap<>();
//        子表达式
        ArrayList<String> childs = new ArrayList<>();
        childs.add(u1);
        childs.add(u2);
        sparam.put("childs",childs);
        ArrayList<String> allCol = new ArrayList<>();
        allCol.addAll(weidu);
        allCol.add("sum(sum_metric_cash) sum_metric_cash");
        allCol.add("sum(income2020) income2020");

        sparam.put("alls",allCol);

        sparam.put(WEIDU,weidu);
        System.out.println(genSql(sigle, sparam));

    }
}
