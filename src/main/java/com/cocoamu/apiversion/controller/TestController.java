package com.cocoamu.apiversion.controller;

import com.cocoamu.apiversion.annotation.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    @ApiVersion("1.0.0")
    public String test0(){
        return "1.0.0";
    }

    @RequestMapping("/test")
    @ApiVersion("1.1.0")
    public String test1(){
        return "1.1.0";
    }

    @RequestMapping("/test")
    @ApiVersion("1.2.0")
    public String test2(){
        return "1.2.0";
    }
}