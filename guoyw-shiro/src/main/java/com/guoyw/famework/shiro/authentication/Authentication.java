package com.guoyw.famework.shiro.authentication;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 身份认证/登录，验证用户是不是拥有相应的身份
 * @author: guoyw
 * create: 2019-12-30 10:40
 **/

public interface Authentication{
  
  boolean supports(Object object);
  
  AuthenticationInfo getAuthenticationInfo(AuthenticationToken token, String realmName);
  
}
