package com.ang.frontui.controller;

import com.ang.frontui.service.CommonFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("filter")
public class FilterController {

    @Autowired
    CommonFilterService commonFilterService;


    @RequestMapping("info")
    public Map getFilters(@RequestParam Long id){
        commonFilterService.genFilter(id);
        return new HashMap();
    }

}
