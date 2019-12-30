package com.guoyw.framework.starter.api_versioning;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @program: guoyw-famework
 * @description: 创建自定义requestMapping类来配置规则
 * @author: guoyw
 * @create: 2019-12-23 22:56
 **/

public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping{
  
  @Override
  protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType){
    // 扫描类上的 @ApiVersion
    ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
    return createRequestCondition(apiVersion);
  }
  
  @Override
  protected RequestCondition<?> getCustomMethodCondition(Method method){
    // 扫描方法上的 @ApiVersion
    ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
    return createRequestCondition(apiVersion);
  }
  
  private RequestCondition<ApiVersionCondition> createRequestCondition(ApiVersion apiVersion){
    if(Objects.isNull(apiVersion))
      return null;
    
    int value = apiVersion.value();
    Assert.isTrue(value >= 1, "Api Version Must be greater than or equal to 1");
    return new ApiVersionCondition(value);
  }
  
}
