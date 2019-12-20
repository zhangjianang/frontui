package com.ang.frontui.service;

import com.ang.frontui.bean.UserInfo;
import com.ang.frontui.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userDao;

    public UserInfo selectUserById(Long id) {
        return userDao.findOne(id);
    }
}
