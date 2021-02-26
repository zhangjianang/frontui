package com.ang.frontui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    FeignOderI feignOderI;

    @RequestMapping("getServiceList")
    public List<ServiceInstance> getServiceList() {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("ang-front-ui");
        return serviceInstanceList;
    }

    @PostMapping("getKey")
    public String getKey(@RequestParam String id) {
        String forObject = restTemplate.postForObject("http://product-center/getKey", null, String.class);
        System.out.println(forObject);
        return forObject;
    }

    @PostMapping("feignGetKey")
    public String feignGetKey(@RequestParam String id) {
        return feignOderI.getSecretKey();
    }
}
