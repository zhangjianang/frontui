package com.ang.frontui.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "product-center")
public interface FeignOderI {

    @PostMapping("getKey")
    String getSecretKey();
}
