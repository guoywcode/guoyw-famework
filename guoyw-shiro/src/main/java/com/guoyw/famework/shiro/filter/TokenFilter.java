package com.guoyw.famework.shiro.filter;

import com.guoyw.famework.shiro.session.Utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.AbstractFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: guoyw
 * create: 2019-12-30 14:09
 **/
@Slf4j
public class TokenFilter extends AbstractFilter{
  
  private String tokenIdentification;
  
  public TokenFilter(String tokenIdentification){
    this.tokenIdentification = tokenIdentification;
  }
  
  
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
    log.debug("exec YioksTokenFilter");
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String token = request.getHeader(tokenIdentification);
    String sessionId = SessionUtil.getSessionId();
    if (StringUtils.isBlank(token) || !token.equalsIgnoreCase(sessionId)) {
      log.debug("YioksTokenFilter - set response header "
        + tokenIdentification + ":" + sessionId);
      response.setHeader(tokenIdentification, sessionId);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
