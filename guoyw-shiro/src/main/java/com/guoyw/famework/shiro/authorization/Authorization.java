package com.guoyw.famework.shiro.authorization;

import org.apache.shiro.authz.AuthorizationInfo;

/**
 * 授权，即权限验证，验证某个已认证的用户是否拥有某个权限；
 * 即判断用户是否能做事情，常见的如：
 *  验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；
 * @author: guoyw
 * create: 2019-12-30 10:58
 **/

public interface Authorization{
  boolean supports(Object object);
  
  AuthorizationInfo getAuthorizationInfo(Object object);
}
