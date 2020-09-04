package com.ang.frontui.controller;

import com.ang.frontui.bean.CommonFilter;
import com.ang.frontui.service.CommonFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("filter")
public class FilterController {

    @Autowired
    CommonFilterService commonFilterService;


    @RequestMapping("info")
    public Map getFilters(@RequestParam Long id){
        CommonFilter commonFilter = commonFilterService.genFilter(id);
        return genRight(true,0,"success",commonFilter);
    }

    @RequestMapping("setCommonFilter")
    public Map setCommonFilter(@RequestBody CommonFilter commonFilter){
        Integer res = commonFilterService.setCommonFilter(commonFilter);
        return genRight(true,0,"success",res);
    }

    @RequestMapping("selectAllCommonFilter")
    public Map selectAllCommonFilter(){
        List<CommonFilter> data = commonFilterService.selectAllCommonFilter();
        return genRight(true,0,"success",data);
    }


    public static Map genRight(boolean success,int code,String msg,Object data){
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("success",success);
        resMap.put("code",code);
        resMap.put("msg",msg);
        resMap.put("data",data);
        return  resMap;
    }

}
