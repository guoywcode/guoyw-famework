package com.guoyw.framework.test.swagger;

import com.guoyw.framework.starter.swagger.SpringFoxSwagger2DocketFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: guoyw
 * create: 2020-01-02 09:46
 **/
@Configuration
@EnableSwagger2
@Profile({"test", "local"})
public class SwaggerConfig{
  
  @Bean
  public SpringFoxSwagger2DocketFactoryBean swaggerTest(){
   return new SpringFoxSwagger2DocketFactoryBean(
     "com.guoyw.framework.test",
     "guoyw-framework-test"
   );
  }
}
