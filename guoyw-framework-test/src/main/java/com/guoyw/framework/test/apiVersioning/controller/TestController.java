package com.guoyw.framework.test.apiVersioning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: guoyw-famework
 * @description:
 * @author: guoyw
 * @create: 2019-12-23 23:03
 **/

@RestController
@RequestMapping("api/test")
public class TestController{

    @GetMapping
    public String test01(@PathVariable String version) {
        return "test01 : " + version;
    }

//    @GetMapping
//    public String test02(@PathVariable String version) {
//        return "test02 : " + version;
//    }
}
