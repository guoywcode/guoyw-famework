package com.guoyw.framework.starter.shiro;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
  DefaultGuoywShiroConfig.class
})
public @interface EnableGuoywShiro{
}
