package com.luikia.controller;

import com.luikia.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @Value("${env}")
    private String env;
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(@RequestParam("a") Integer a,@RequestParam("b") Integer b){
        System.out.println(env);
        return testService.add(a,b);
    }

}
