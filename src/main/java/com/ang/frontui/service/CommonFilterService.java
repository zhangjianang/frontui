package com.ang.frontui.service;

import com.ang.frontui.bean.CommonFilter;
import com.ang.frontui.mapper.CommonFilterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonFilterService {
    @Autowired
    CommonFilterMapper commonFilterMapper;

    public CommonFilter genFilter(Long id){
         return commonFilterMapper.findByTableId(id);
    }

    public Integer setCommonFilter(CommonFilter commonFilter){
        return commonFilterMapper.setCommonFilter(commonFilter);
    }

    public List<CommonFilter> selectAllCommonFilter(){
        return commonFilterMapper.selectAllCommonFilter();
    }
}
