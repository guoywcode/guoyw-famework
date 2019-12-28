package com.guoyw.framework.starter.exception.annotation;
import com.guoyw.framework.starter.exception.GuoywExceptionConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
/**
 * @author: guoyw
 * created: 2019-12-27 18:36
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
  GuoywExceptionConfiguration.class
})
public @interface EnableGuoywExceptionHandler{
}
