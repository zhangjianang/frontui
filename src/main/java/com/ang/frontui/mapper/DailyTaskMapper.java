package com.ang.frontui.mapper;

import com.ang.frontui.bean.DailyTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DailyTaskMapper {
    List<DailyTask> findAll();

    Integer addOne(DailyTask daily);

    Integer updateById(DailyTask dailyTask);

//    List<DailyTask> selectIds(@Param("ids") List<Integer> ids);
}
