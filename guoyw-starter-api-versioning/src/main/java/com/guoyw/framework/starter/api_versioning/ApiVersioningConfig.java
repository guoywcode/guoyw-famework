package com.guoyw.framework.starter.api_versioning;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @program: guoyw-famework
 * @description: 覆盖spring原生RequestMappingHandlerMapping类
 * @author: guoyw
 * @create: 2019-12-23 23:01
 **/

public class ApiVersioningConfig implements WebMvcRegistrations{
    
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        return handlerMapping;
    }

//    @Override
//    public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
//        return new CustomRequestMappingHandlerMapping();
//    }
}
