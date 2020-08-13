package com.ang.frontui.controller;


import com.alibaba.fastjson.JSONObject;
import com.ang.frontui.bean.QueryInfo;
import com.ang.frontui.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    QueryService queryService;

    @RequestMapping("/info")
    public Map genSql(@RequestBody QueryInfo queryInfo){
        System.out.println(JSONObject.toJSONString(queryInfo));
        return genRes(queryService.genSql(queryInfo));
    }


    public static Map genRes(Object data){
        HashMap<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("success",true);
        res.put("message","");
        return res;
    }
}
