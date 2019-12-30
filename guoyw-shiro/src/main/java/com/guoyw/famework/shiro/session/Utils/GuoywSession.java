package com.guoyw.famework.shiro.session.Utils;

public interface GuoywSession{

  void setSession(String key, Object value);

  Object getSession(String key);

  Object removeSession(String key);

  String getSessionId();
}
