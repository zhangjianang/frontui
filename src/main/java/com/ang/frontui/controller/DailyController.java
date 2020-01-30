package com.ang.frontui.controller;

import com.alibaba.fastjson.JSONObject;
import com.ang.frontui.bean.DailyTask;
import com.ang.frontui.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/daily")
public class DailyController {

    public static final String DAILY_KEY="daily";

    @Autowired
    DailyService dailyService;


    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/all")
    public String findAll(){

        Object daily = redisTemplate.opsForValue().get(DAILY_KEY);
         if(daily != null){
            System.out.println("daily 缓存获取！");
            return daily.toString();
        }else{
            String s = JSONObject.toJSONString(dailyService.findAll());
            redisTemplate.opsForValue().set(DAILY_KEY,s);
            return s;
        }
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public String add(@RequestParam(value="info") String info){
        System.out.println(info);
        DailyTask dailyTask = JSONObject.parseObject(info, DailyTask.class);
        if(dailyTask.getTaskName()==null||"".equals(dailyTask.getTaskName())){
            return "param not set";
        }
        dailyService.addOne(dailyTask);
        return "ok";
    }

    @RequestMapping(value="/update",method= RequestMethod.POST)
    public String update(@RequestParam (value="info") String info){
        DailyTask dailyTask = JSONObject.parseObject(info, DailyTask.class);
        dailyService.updateById(dailyTask);
        return "ok";
    }

    @RequestMapping(value="/updatebean",method= RequestMethod.POST)
    public String updatebean(@RequestParam Map<String, Object> params){
        System.out.println(params);
        Object id = params.get("id");
        int uid = Integer.parseInt(id.toString());
        DailyTask dailyTask = dailyService.findById(uid);
        dailyTask.setTaskName(params.get("taskName").toString());
        dailyTask.setCategory(params.get("category").toString());
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date  date = format.parse(params.get("date").toString());
            long dt = date.getTime();
            dailyTask.setDate(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer res = dailyService.updateById(dailyTask);
        return res+"";
    }
}
