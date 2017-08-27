package com.luikia.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "add" , method = RequestMethod.GET)
    @ResponseBody
    public String add(@RequestParam("a") Integer a, @RequestParam("b") Integer b){
        return String.valueOf(a+b);
    }


}
