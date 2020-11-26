package com.ang.frontui.report;


import java.util.List;

public class FindNameVisitor implements Visitor {
    private List<String> keys;

    public FindNameVisitor(List<String> keys){
        this.keys = keys;
    }


    @Override
    public String visit(Register register ) {
        System.out.println(register.getName());
        if(register.getCode().equals(keys.get(register.getLevel()))){
            if(register.getChild()!=null && register.getChild().size()>0){
                for(Register per : register.getChild()){
                    String accept = per.accept(this);
                    if(accept!=null){
                        return accept;
                    }
                }
                return register.getDefault();
            }else{
                return register.getName();
            }
        }
        return null;
    }
}
