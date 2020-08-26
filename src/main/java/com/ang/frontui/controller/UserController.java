package com.ang.frontui.controller;

import com.alibaba.fastjson.JSONObject;
import com.ang.frontui.bean.PageInfo;
import com.ang.frontui.bean.UserInfo;
import com.ang.frontui.bean.UserMeasure;
import com.ang.frontui.common.AngRedisNotify;
import com.ang.frontui.mapper.UserMapper;
import com.ang.frontui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("user")
public class UserController {

    private static final int EXP_SECOND = 60*60;

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate<String,  String> redisTemplate;



    @RequestMapping(value = "list")
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
            redisString.set("list",JSONObject.toJSONString(userList),EXP_SECOND, TimeUnit.SECONDS);
            System.out.println("直接 ！");
        }else{
            userList = JSONObject.parseArray(list,String.class);
            System.out.println("缓存获取！");
        }
        return userList;
    }


    @RequestMapping("/id/{id}")
    public String findone(@PathVariable Long id){
        String key = "id-"+id;
        String res = redisTemplate.opsForValue().get(key);
        if(res == null){
            res = JSONObject.toJSONString(userService.selectUserById(id));
            redisTemplate.opsForValue().set(key,res,EXP_SECOND, TimeUnit.SECONDS);
        }else{
            System.out.println("缓存获取！");
        }
        return res;
    }

    @RequestMapping("/del/{id}")
    public String deleteById(@NotNull @PathVariable Long id){
        Integer integer = userService.deleteById(id);
        return integer+"";
    }

    @RequestMapping("del")
    public String delById( @RequestBody UserInfo userInfo){
        Integer integer = userService.deleteById(userInfo.getId());
        return integer+"";
    }


    @RequestMapping("add")
    public String addUser(@Valid @RequestBody UserInfo userInfo){
        userInfo.setDate(System.currentTimeMillis());
        userInfo.setState(1);
        Integer integer = userService.addUser(userInfo);
        return integer+"";
    }

    @RequestMapping("all")
    public List<UserInfo> selectAll(){
        List<UserInfo> userInfos = userService.selectAll();
        System.out.println(JSONObject.toJSONString(userInfos));
        return userInfos;
    }

    @RequestMapping("measure")
    public List<UserMeasure> selectMeasure(@RequestBody PageInfo pageInfo){
        List<UserMeasure> userMeasures = userService.selectMeasure(pageInfo.getStart(),pageInfo.getPageSize());
        return userMeasures;
    }

    @RequestMapping("measurePageNum")
    public Integer selectMeasureTotal(){
        Integer num = userService.selectMeasureTotal();
        System.out.println("measure 总数："+num);
        return num;
    }
}
