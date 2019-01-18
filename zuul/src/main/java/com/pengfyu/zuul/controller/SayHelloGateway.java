package com.pengfyu.zuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloGateway {

    @RequestMapping("/hello")
    public String hello(){
        return "you eat it ...";
    }

}
