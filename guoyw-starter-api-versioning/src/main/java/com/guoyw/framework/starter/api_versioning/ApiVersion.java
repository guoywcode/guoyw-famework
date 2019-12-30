package com.guoyw.framework.starter.api_versioning;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @program: guoyw-famework
 * @description:
 * @author: guoyw
 * @create: 2019-12-23 22:44
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    
    int value();
}
