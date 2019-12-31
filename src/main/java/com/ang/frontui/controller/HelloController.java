package com.ang.frontui.controller;

import com.alibaba.fastjson.JSONObject;
import com.ang.frontui.bean.UserInfo;
import com.ang.frontui.mapper.UserMapper;
import com.ang.frontui.service.DailyService;
import com.ang.frontui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class HelloController {

    @Autowired
    UserService userService;

    @Autowired
    DailyService dailyService;

    @RequestMapping(value = "/list")
    public List<String> list() {
        List<String> userList = new ArrayList<>();
        userList.add("we are the champion</br>");
        userList.add("we keep on fighting till the end</br>");
        userList.add("we are the champion</br>");
        userList.add("we are the champion</br>");
        userList.add("no time for loser</br>");
        userList.add("cause we are the champion of the world</br>");
        return userList;
    }


    @RequestMapping("/id/{id}")
    public String findone(@PathVariable Long id){
        return JSONObject.toJSONString(userService.selectUserById(id));
    }


    @RequestMapping("/daily")
    public String findAll(){
        return JSONObject.toJSONString(dailyService.findAll());
    }

}
