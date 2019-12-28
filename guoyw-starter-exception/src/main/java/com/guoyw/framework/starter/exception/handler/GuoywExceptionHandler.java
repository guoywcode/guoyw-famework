package com.guoyw.framework.starter.exception.handler;
import com.guoyw.framework.base.exception.GuoywException;
import com.guoyw.framework.starter.exception.properties.GuoywExceptionTipsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
  * @author: guoyw
  * created: 2019-12-27 18:36
  */
@RestControllerAdvice
@Slf4j
public class GuoywExceptionHandler{
  
  @Autowired(required = false)
  protected MessageSource messageSource;
  @Autowired(required = false)
  private GuoywExceptionTipsProperties tipsProperties;
  
  @ResponseBody
  @ExceptionHandler(GuoywException.class)
  public ResponseEntity<Object> handlerGuoywException(GuoywException exception) {
    Map<String, Object> result = new HashMap<>();
    log.warn(exception.getClass().getName());
    log.warn(exception.getMessage());
    
    String code = exception.getMessage();
    String msgKey = exception.getClass().getSimpleName() + "." + code;
    
    String msg;
    if (messageSource != null) {
      msg = messageSource.getMessage(msgKey, exception.getParams(), code, LocaleContextHolder.getLocale());
    } else {
      msg = code;
    }
    result.put(tipsProperties.getCodeName(), code);
    result.put(tipsProperties.getMsgName(), msg);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
  
  
}
