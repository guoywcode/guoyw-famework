package com.guoyw.framework.starter.shiro;

import com.guoyw.famework.shiro.factoryBeans.SpringShiroFilterRegistrationBean;
import com.guoyw.famework.shiro.persistence.RoleAndPermissionPersistenceBeanPostProcessor;
import com.guoyw.famework.shiro.properties.SessionProperties;
import com.guoyw.famework.shiro.realm.AuthorizationRealm;
import com.guoyw.famework.shiro.session.Utils.DefaultSession;
import com.guoyw.famework.shiro.session.Utils.GuoywSession;
import com.guoyw.famework.shiro.session.cache.RedissonSessionCache;
import com.guoyw.famework.shiro.session.dao.RedissonSessionDAO;
import com.guoyw.famework.shiro.session.manager.SessionManager;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.Serializable;

/**
 * @author: guoyw
 * create: 2019-12-30 13:51
 **/
@Configuration
public class DefaultGuoywShiroConfig{
  
  
  
  
  
  @Bean(name = "shiroFilter")
  @ConditionalOnMissingBean(name = "shiroFilter")
  public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager defaultWebSecurityManager) {
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    shiroFilter.setSecurityManager(defaultWebSecurityManager);
    return shiroFilter;
  }
  
  @Bean
  @ConditionalOnMissingBean
  public GuoywSession sessionUtil() {
    return new DefaultSession();
  }
  
  @Bean
  @ConditionalOnMissingBean(Realm.class)
  public AuthorizationRealm defaultAuthorizationRealm(DefaultWebSecurityManager defaultWebSecurityManager) {
    AuthorizationRealm realm = new AuthorizationRealm();
    defaultWebSecurityManager.setRealm(realm);
    return realm;
  }
  
  @Bean
  public SessionProperties shiroSessionProperties() {
    return new SessionProperties();
  }
  
  
  @Bean
  public SessionManager sessionManager(SessionProperties sessionProperties, RedissonClient redissonClient, DefaultWebSecurityManager defaultWebSecurityManager) {
    
    SessionManager sessionManager = new SessionManager();
    sessionManager.setTokenIdentification(sessionProperties.getTokenIdentification());
    sessionManager.setGlobalSessionTimeout(sessionProperties.getTimeout());
    sessionManager.setDeleteInvalidSessions(true);
    sessionManager.setSessionValidationSchedulerEnabled(true);
    sessionManager.setSessionValidationInterval(sessionProperties.getSessionValidationInterval());
    
    // 把 GuoywSession 存储到 redis 数据库中
    RMapCache<Serializable, Session> rMap = redissonClient.getMapCache(sessionProperties.getRedissonStoreName());
    RMapCache<Serializable, Session> rMapCache = redissonClient.getMapCache(sessionProperties.getRedissonStoreName() + "-cache");
    EnterpriseCacheSessionDAO sessionDAO = new RedissonSessionDAO(rMap);
    sessionManager.setSessionDAO(sessionDAO);
    sessionManager.setCacheManager(new AbstractCacheManager() {
      @Override
      protected Cache createCache(String s) throws CacheException{
        return new RedissonSessionCache(rMapCache);
      }
    });
    
    defaultWebSecurityManager.setSessionManager(sessionManager);
    return sessionManager;
  }
  
  @Bean
  @ConditionalOnMissingBean(SecurityManager.class)
  public DefaultWebSecurityManager defaultWebSecurityManager() {
    return new DefaultWebSecurityManager();
  }
  
  // 支持 Shiro 注解权限控制
  @Bean
  @DependsOn("lifecycleBeanPostProcessor")
  public AuthorizationAttributeSourceAdvisor advisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }
  
  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }
  
  @Bean
  @ConditionalOnMissingBean
  public RoleAndPermissionPersistenceBeanPostProcessor PersistenceBeanPostProcessor() {
    return new RoleAndPermissionPersistenceBeanPostProcessor();
  }
  
  @Bean
  public SpringShiroFilterRegistrationBean springShiroFilterRegistrationBean() {
    return new SpringShiroFilterRegistrationBean();
  }
  
}
