package com.guoyw.famework.shiro.realm;

import com.guoyw.famework.shiro.authentication.Authentication;
import com.guoyw.famework.shiro.authorization.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: guoyw
 * create: 2019-12-30 14:46
 **/
@Slf4j
public class AuthorizationRealm extends AuthorizingRealm implements BeanPostProcessor{
  
  protected List<Authentication> authentications;
  protected List<Authorization> authorizations;
  
  public AuthorizationRealm() {
    this.authentications = new ArrayList<>();
    this.authorizations = new ArrayList<>();
  }
  
  @Override
  public boolean supports(AuthenticationToken token) {
    for (Authentication authorizationService : authentications) {
      if (authorizationService.supports(token)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
    Object principal = principals.getPrimaryPrincipal();
    for (Authorization authorizationService : authorizations) {
      if (authorizationService.supports(principal)) {
        return authorizationService.getAuthorizationInfo(principal);
      }
    }
    log.warn("No supported Authorization found");
    return null;
  }
  
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
    for (Authentication authentication : authentications) {
      if (authentication.supports(authenticationToken)) {
        return authentication.getAuthenticationInfo(authenticationToken, getName());
      }
    }
    log.warn("No supported Authentication found");
    return null;
  }
  
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException{
    if (bean instanceof Authentication) {
      authentications.add((Authentication) bean);
    }
    if (bean instanceof Authorization) {
      authorizations.add((Authorization) bean);
    }
    return bean;
  }
}
