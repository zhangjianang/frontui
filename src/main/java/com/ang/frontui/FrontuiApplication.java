package com.ang.frontui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.ang.frontui.mapper")
public class FrontuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontuiApplication.class, args);
	}

}
