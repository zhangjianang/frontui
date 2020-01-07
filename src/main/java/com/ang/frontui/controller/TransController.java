package com.ang.frontui.controller;

import com.ang.frontui.bean.UserInfo;
import com.ang.frontui.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trans")
public class TransController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/first")
    @Transactional(propagation = Propagation.REQUIRED)
    public void  first(){
        ((TransController) AopContext.currentProxy()).second();
        System.out.println(1/0);
    }
    @Transactional(propagation = Propagation.SUPPORTS )
    public void second(){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("事务111");
        userService.save(userInfo);
    }
}
