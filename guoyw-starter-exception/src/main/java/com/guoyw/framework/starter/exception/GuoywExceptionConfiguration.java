package com.guoyw.framework.starter.exception;


import com.guoyw.framework.starter.exception.properties.GuoywExceptionTipsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: guoyw
 * created: 2019-12-27 18:36
 */

@Configuration
public class GuoywExceptionConfiguration{
  @Bean
  @ConditionalOnMissingBean
  public GuoywExceptionTipsProperties guoywExceptionTipsProperties() {
    return new GuoywExceptionTipsProperties();
  }
}
