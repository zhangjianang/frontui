package com.ang.frontui.service;

import com.ang.frontui.bean.DailyTask;
import com.ang.frontui.mapper.DailyTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyService {
    @Autowired
    DailyTaskMapper dailyTaskMapper;

    public List<DailyTask> findAll(){
        return dailyTaskMapper.findAll();
    }

    public Integer addOne(DailyTask daily){
        return dailyTaskMapper.addOne(daily);
    }

    public Integer updateById(DailyTask dailyTask){
        return dailyTaskMapper.updateById(dailyTask);
    }

    public DailyTask findById(Integer id){
        return dailyTaskMapper.findById(id);
    }
}
