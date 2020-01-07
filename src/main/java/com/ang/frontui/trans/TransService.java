package com.ang.frontui.trans;

import com.ang.frontui.FrontuiApplication;
import com.ang.frontui.bean.UserInfo;
import com.ang.frontui.config.DruidDataSourceConfig;
import com.ang.frontui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransService {



    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        TransService bean = context.getBean(TransService.class);

    }
}
