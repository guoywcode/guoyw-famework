package com.guoyw.famework.shiro.factoryBeans;

import com.guoyw.famework.shiro.properties.SessionProperties;
import com.guoyw.famework.shiro.session.cache.RedissonSessionCache;
import com.guoyw.famework.shiro.session.dao.RedissonSessionDAO;
import com.guoyw.famework.shiro.session.manager.SessionManager;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author: guoyw
 * create: 2019-12-30 14:25
 **/

public class WebSecurityManagerFactoryBean implements FactoryBean<SecurityManager>{
  
  @Autowired
  private Realm realm;
  
  @Autowired(required = false)
  private RedissonClient redissonClient;
  
  @Autowired
  private SessionProperties sessionProperties;
  
  @Override
  public SecurityManager getObject(){
    Assert.notNull(realm, "No Realm was found available");
  
    // 实例化 SessionManager
    SessionManager sessionManager = new SessionManager();
    sessionManager.setTokenIdentification(sessionProperties.getTokenIdentification());
    sessionManager.setGlobalSessionTimeout(sessionProperties.getTimeout());
    sessionManager.setDeleteInvalidSessions(true);
    sessionManager.setSessionValidationSchedulerEnabled(true);
    sessionManager.setSessionValidationInterval(sessionProperties.getSessionValidationInterval());
  
    // 如果 RedissonClient 实例存在，测使用 redis 进行 GuoywSession 存储 以实现分布式服务的Session 同步
    if (redissonClient != null) {
      RMapCache rMap = redissonClient.getMapCache(sessionProperties.getRedissonStoreName());
      RMapCache rMapCache = redissonClient.getMapCache(sessionProperties.getRedissonStoreName() + "-cache");
      EnterpriseCacheSessionDAO sessionDAO = new RedissonSessionDAO(rMap);
      sessionManager.setSessionDAO(sessionDAO);
      sessionManager.setCacheManager(new AbstractCacheManager() {
        @Override
        protected Cache createCache(String s) throws CacheException{
          return new RedissonSessionCache(rMapCache);
        }
      });
    }
  
    // 实例化 DefaultWebSecurityManager
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(realm);
    securityManager.setSessionManager(sessionManager);
    
    return securityManager;
  }
  
  @Override
  public Class<?> getObjectType(){
    return SecurityManager.class;
  }
}
