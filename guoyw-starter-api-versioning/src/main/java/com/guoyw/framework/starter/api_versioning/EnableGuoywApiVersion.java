package com.guoyw.framework.starter.api_versioning;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;
/**
 * @program: guoyw-famework
 * @description:
 * @author: guoyw
 * @create: 2019-12-24 02:03
 **/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApiVersioningConfig.class})
public @interface EnableGuoywApiVersion{
}
