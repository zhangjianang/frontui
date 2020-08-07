package com.ang.frontui.config;

import com.ang.frontui.interceptors.AngInterceptor;
import com.ang.frontui.interceptors.FirstInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    FirstInterceptor firstInterceptor;

    @Autowired
    AngInterceptor angInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(firstInterceptor);

        List<String> list = new ArrayList<>();
        registry.addInterceptor(angInterceptor).addPathPatterns("/data/**").excludePathPatterns(list);
    }
}
