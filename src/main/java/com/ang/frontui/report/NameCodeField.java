package com.ang.frontui.report;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class NameCodeField {
    String name;
    String code;
    List<NameCodeField> child;
    String childDefalut;

    public static void main(String[] args) {
        String str= "{\"name\":\"parent\",\"code\":'0',\"childDefault\":\"未分类\",\"child\":[{\"name\":\"一\",\"code\":\"1\"},{\"name\":\"二\",\"code\":\"2\",\"childDefault\":\"二未分类\",\"child\":[{\"name\":\"二一\",\"code\":\"21\"}]},{\"name\":\"三\",\"code\":3}]}";
        NameCodeField nameCodeField = JSONObject.parseObject(str, NameCodeField.class);
        System.out.println("res:"+iterShow(nameCodeField, "21"));

    }

    private static String iterShow(NameCodeField nameCodeField,String code){
        System.out.println(nameCodeField.getName());
        if(nameCodeField.getCode().equals(code)||nameCodeField.getCode().equals("0")){
            if(nameCodeField.child!=null && nameCodeField.child.size()>0){
                for(NameCodeField per:nameCodeField.child){
                    String s = iterShow(per, code);
                    if(!s.equals("-1")){
                        return s;
                    }
                }
                return nameCodeField.childDefalut;
            }else {
                return nameCodeField.getName();
            }
        }
        return "-1";
    }
}
