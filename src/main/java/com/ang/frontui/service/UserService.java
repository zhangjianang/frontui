package com.ang.frontui.service;

import com.ang.frontui.bean.UserInfo;
import com.ang.frontui.bean.UserMeasure;
import com.ang.frontui.common.AngRedisNotify;
import com.ang.frontui.mapper.UserMapper;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class UserService {

    @Autowired
    UserMapper userDao;

    public UserInfo selectUserById(Long id) {
        return userDao.findOne(id);
    }

    @AngRedisNotify
    public Integer deleteById(Long id){
        return userDao.deleteById(id);
    }

    public Integer addUser(UserInfo userInfo){
        return userDao.save(userInfo);
    }

    public List<UserInfo> selectAll(){
        return userDao.selectAll();
    }

    public List<UserMeasure> selectMeasure(Integer start,Integer end){
        return userDao.selectMeasure(start,end);
    }

    public Integer selectMeasureTotal(){
        return userDao.selectMeasureTotal();
    }
}
