package com.monetware.bda.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {

        return "hello index";
    }

    @RequestMapping("/*")
    public String other() {

        return "hello other page";
    }

    @RequestMapping("responseBody")
    @ResponseBody
    public String responseBody() {

        return "hello responseBody";
    }


}
