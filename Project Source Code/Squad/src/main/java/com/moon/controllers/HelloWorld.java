package com.moon.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@Controller
@RestController
class HelloWorld {
    @RequestMapping("/hello")
    public String helloWorld(@RequestParam(value = "name") String name){
        return "<h1> Hello World </h1>" + "Hello " + name ;
    }
}
