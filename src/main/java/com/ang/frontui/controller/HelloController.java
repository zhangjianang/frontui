package com.ang.frontui.controller;

import com.alibaba.fastjson.JSONObject;
import com.ang.frontui.bean.UserInfo;
import com.ang.frontui.mapper.UserMapper;
import com.ang.frontui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/data")
public class HelloController {

    @Autowired
    UserService userService;


    @Autowired
    RedisTemplate<String,  String> redisTemplate;



    @RequestMapping(value = "/list")
    public List<String> list() {
        ValueOperations<String, String> redisString  = redisTemplate.opsForValue();
        String list = redisString.get("list");
        List<String> userList = new ArrayList<>();
        if(list==null){
            userList.add("we are the champion</br>");
            userList.add("we keep on fighting till the end</br>");
            userList.add("we are the champion</br>");
            userList.add("we are the champion</br>");
            userList.add("no time for loser</br>");
            userList.add("cause we are the champion of the world</br>");
            redisString.set("list",JSONObject.toJSONString(userList),20, TimeUnit.SECONDS);
            System.out.println("直接 ！");
        }else{
            userList = JSONObject.parseArray(list,String.class);
            System.out.println("缓存获取！");
        }
        return userList;
    }


    @RequestMapping("/id/{id}")
    public String findone(@PathVariable Long id){
        String andSet = redisTemplate.opsForValue().getAndSet("findone-id", JSONObject.toJSONString(userService.selectUserById(id)));
        return andSet;
    }

}
