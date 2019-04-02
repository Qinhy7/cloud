package com.toni.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/28 0028 11:31
 */
@RestController
public class TestController {

//    @Value("${env}")
//    private String env;

    @GetMapping("/hello")
    public String hello(){
        return "sss";
    }

}
