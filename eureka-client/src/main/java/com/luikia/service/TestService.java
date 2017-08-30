package com.luikia.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String add(Integer a,Integer b){
        String format = "http://EUREKA-REGISTER/test/add?a=%d&b=%d";
        String s = restTemplate.getForObject(String.format(format,a,b),String.class);
        return s;
    }

    public String addServiceFallback(Integer a,Integer b){
        return "error"+a.toString()+b.toString();
    }
}
