package com.toni.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 14:54
 */
@RestController
@RefreshScope // 配置bus的刷新
public class HelloController {

    @Value("${env}")
    private String env;

    @RequestMapping("/pro")
    public String test(){
        return "product msg2...."+env;
    }

}
