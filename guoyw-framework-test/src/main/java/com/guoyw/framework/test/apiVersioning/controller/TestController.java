package com.guoyw.framework.test.apiVersioning.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "测试接口")
public class TestController{

    @GetMapping
    @ApiOperation("测试")
    public String test01(@PathVariable String version) {
        return "test01 : " + version;
    }

//    @GetMapping
//    public String test02(@PathVariable String version) {
//        return "test02 : " + version;
//    }
}
