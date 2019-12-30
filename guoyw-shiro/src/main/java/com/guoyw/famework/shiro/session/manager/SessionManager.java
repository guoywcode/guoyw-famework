package com.guoyw.famework.shiro.session.manager;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中
 * @author: guoyw
 * create: 2019-12-30 11:05
 **/

public class SessionManager extends DefaultWebSessionManager{
  
  private String tokenIdentification = "COMMON-SHIRO-TOKEN";
  
  public SessionManager() {
    super();
  }
  
  
  public String getTokenIdentification(){
    return tokenIdentification;
  }
  
  public void setTokenIdentification(String tokenIdentification){
    this.tokenIdentification = tokenIdentification;
    Cookie cookie = new SimpleCookie(this.tokenIdentification);
    cookie.setHttpOnly(true);
    this.setSessionIdCookie(cookie);
  }
  
  @Override
  protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
    String id = WebUtils.toHttp(request).getHeader(tokenIdentification);
    
    if (!StringUtils.isEmpty(id)) {
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "Stateless request");
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
      return id;
    } else {
      //否则按默认规则从cookie取sessionId
      return super.getSessionId(request, response);
    }
  }
  
}
