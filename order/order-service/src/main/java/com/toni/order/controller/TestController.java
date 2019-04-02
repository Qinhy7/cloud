package com.toni.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 14:56
 */
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/ord")
    public String test(){

        String object = restTemplate.getForObject("http://PRODUCT/pro", String.class);
        return object;
    }

}
