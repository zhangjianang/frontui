package com.ang.frontui.mapper;

import com.ang.frontui.bean.CommonFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommonFilterMapper {

    @Select("select * from common_filter where table_id = #{tableId}")
    CommonFilter findByTableId(Long id);

}
