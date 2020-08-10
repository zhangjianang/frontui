package com.ang.frontui.config;

import com.ang.frontui.interceptors.AngInterceptor;
import com.ang.frontui.interceptors.CorsInterceptor;
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

    @Autowired
    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor);
//        registry.addInterceptor(firstInterceptor);

        List<String> list = new ArrayList<>();
        registry.addInterceptor(angInterceptor).addPathPatterns("/user/**").excludePathPatterns(list);
    }
}
