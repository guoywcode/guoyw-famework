package com.guoyw.famework.shiro.session.Utils;


import org.apache.shiro.session.Session;

public abstract class AbstractGuoywSession implements GuoywSession{

  protected abstract Session getSessionObject();
  
  @Override
  public void setSession(String key, Object value) {
    getSessionObject().setAttribute(key, value);
  }

  @Override
  public Object getSession(String key) {
    return getSessionObject().getAttribute(key);
  }

  @Override
  public Object removeSession(String key) {
    return getSessionObject().removeAttribute(key);
  }

  @Override
  public String getSessionId() {
    return getSessionObject().getId().toString();
  }
}
