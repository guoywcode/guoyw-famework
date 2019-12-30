package com.guoyw.famework.shiro.session.Utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @author: guoyw
 * create: 2019-12-30 14:14
 **/

public class SessionUtil{
  
  public static void setSession(String key, Object value){
    getSession().setAttribute(key,value);
  }
  
  public static Object getSession(String key){
    return getSession().getAttribute(key);
  }
  
  public static Object removeSession(String key){
    return getSession().removeAttribute(key);
  }
  
  public static String getSessionId(){
    return getSession().getId().toString();
  }
  
  private static Session getSession(){
    return SecurityUtils.getSubject().getSession();
  }
  
}
