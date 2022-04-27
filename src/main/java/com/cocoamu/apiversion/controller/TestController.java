package com.cocoamu.apiversion.controller;

import com.cocoamu.apiversion.annotation.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiVersion("1.2.0")
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "default";
    }

    @RequestMapping("/test")
    @ApiVersion("1.1.0")
    public String test0(){
        return "1.0.0";
    }

    @RequestMapping("/test")
    @ApiVersion("1.4.0")
    public String test2(){
        return "1.2.0";
    }

    @RequestMapping("/test")
    @ApiVersion("1.5.0")
    public String test1(){
        return "1.1.0";
    }


}