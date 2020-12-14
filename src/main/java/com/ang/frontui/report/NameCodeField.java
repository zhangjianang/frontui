package com.ang.frontui.report;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Data
public class NameCodeField implements Register{
    String name;
    String code;
    List<NameCodeField> child;
    String childDefalut;
    Integer level;

    public static void main(String[] args) {
        String str= "{\"name\":\"parent\",\"code\":'0',\"childDefault\":\"未分类\",\"level\":0,\"child\":[{\"name\":\"一\",\"level\":1,\"code\":\"1\"},{\"name\":\"二\",\"code\":\"2\",\"level\":1,\"childDefault\":\"二未分类\",\"child\":[{\"name\":\"二一\",\"level\":2,\"code\":\"21\"}]},{\"level\":1,\"name\":\"三\",\"code\":3}]}";
        NameCodeField nameCodeField = JSONObject.parseObject(str, NameCodeField.class);

        String[] input = new String[]{"0","2","21"};
        List<String> ins = Arrays.asList(input);
        FindNameVisitor findNameVisitor = new FindNameVisitor(ins);
//        System.out.println("res:"+findNameVisitor.visit(nameCodeField));

//        System.out.println("res:"+iterShow(nameCodeField, ins));
        System.out.println("res:"+stackIterShow(nameCodeField, ins));
    }

    private static String iterShow(Register nameCodeField,List<String> keys){
        System.out.println(nameCodeField.getName());
        if(nameCodeField.getCode().equals(keys.get(nameCodeField.getLevel()))){
            if(nameCodeField.getChild()!=null && nameCodeField.getChild().size()>0){
                for(Register per:nameCodeField.getChild()){
                    String s = iterShow(per, keys);
                    if(s!=null){
                        return s;
                    }
                }
                return nameCodeField.getDefault();
            }else {
                return nameCodeField.getName();
            }
        }
        return null;
    }


    private static String stackIterShow(Register root,List<String> keys){

        Stack<Register> stack = new Stack<>();
        stack.push(root);
        while(stack.peek()!=null){
            Register nameCodeField= stack.pop();
            System.out.println(nameCodeField.getName());
            if(nameCodeField.getCode().equals(keys.get(nameCodeField.getLevel()))){
                if(nameCodeField.getChild()!=null && nameCodeField.getChild().size()>0){
                    for(Register per:nameCodeField.getChild()){
                        stack.push(per);
                    }
//                    return nameCodeField.getDefault();
                }else {
                    return nameCodeField.getName();
                }
            }
        }
        return null;
    }


    @Override
    public String accept(Visitor visitor) {
       return visitor.visit(this);
    }

    @Override
    public String getDefault() {
        return this.childDefalut;
    }

    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }

    public Integer getLevel(){
        return level;
    }

    public List getChild(){
        return child;
    }
}
