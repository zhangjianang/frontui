package com.ang.frontui.mapper;

import com.ang.frontui.bean.CommonFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommonFilterMapper {

//    @Select("select * from ang_common_filter where id = #{id}")
    CommonFilter findByTableId(Long id);

    Integer setCommonFilter(CommonFilter commonFilter);

    List<CommonFilter> selectAllCommonFilter();
}
