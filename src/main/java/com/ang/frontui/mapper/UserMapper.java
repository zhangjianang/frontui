package com.ang.frontui.mapper;

import com.ang.frontui.bean.UserInfo;
import com.ang.frontui.bean.UserMeasure;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user_info where id = #{id}")
    UserInfo findOne(Long id);

    @Select("select * from user_info")
    List<UserInfo> selectAll();

    @Insert("INSERT INTO USER_INFO(NAME, GENDER, ADDRESS,DATE,STATE) VALUES(#{name}, #{gender}, #{address},#{date},#{state})")
    int save(UserInfo dept);

    @Delete("delete from user_info where id = #{id}")
    int deleteById(Long id);


    @Select("select id,measure_name as measureName,measure_def as measureDef from user_measure ")
    List<UserMeasure> selectMeasure();

}
