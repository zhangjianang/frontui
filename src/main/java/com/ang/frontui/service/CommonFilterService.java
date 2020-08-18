package com.ang.frontui.service;

import com.ang.frontui.mapper.CommonFilterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonFilterService {
    @Autowired
    CommonFilterMapper commonFilterMapper;

    public void genFilter(Long id){
        commonFilterMapper.findByTableId(id);
    }
}
