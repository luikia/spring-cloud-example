package com.luikia.controller;

import com.luikia.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(@RequestParam("a") Integer a,@RequestParam("b") Integer b){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("show tables");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testService.add(a,b);
    }

}
