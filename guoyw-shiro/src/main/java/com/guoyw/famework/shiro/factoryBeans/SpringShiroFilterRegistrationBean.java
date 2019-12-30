package com.guoyw.famework.shiro.factoryBeans;

import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import javax.annotation.PostConstruct;
import javax.servlet.DispatcherType;


public class SpringShiroFilterRegistrationBean extends FilterRegistrationBean<AbstractShiroFilter> {
  @Autowired
  private AbstractShiroFilter shiroFilter;

  @PostConstruct
  public void init() {
    setFilter(shiroFilter);
    setDispatcherTypes(
      DispatcherType.FORWARD,
      DispatcherType.INCLUDE,
      DispatcherType.REQUEST,
      DispatcherType.ASYNC,
      DispatcherType.ERROR
    );
  }
}
