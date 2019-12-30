package com.guoyw.famework.shiro.factoryBeans;

import com.guoyw.famework.shiro.properties.SessionProperties;
import com.guoyw.famework.shiro.session.cache.RedissonSessionCache;
import com.guoyw.famework.shiro.session.dao.RedissonSessionDAO;
import com.guoyw.famework.shiro.session.manager.SessionManager;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author: guoyw
 * create: 2019-12-30 11:02
 **/

public class SessionManagerFactoryBean implements FactoryBean<SessionManager>{
  
  @Autowired(required = false)
  private RedissonClient redissonClient;
  
  @Autowired
  private SessionProperties properties;
  
  @Override
  public SessionManager getObject() throws Exception{
    SessionManager sessionManager = new SessionManager();
    sessionManager.setTokenIdentification(properties.getTokenIdentification());
    sessionManager.setGlobalSessionTimeout(properties.getTimeout());
    sessionManager.setDeleteInvalidSessions(true);
    sessionManager.setSessionValidationSchedulerEnabled(true);
    sessionManager.setSessionValidationInterval(properties.getSessionValidationInterval());
  
    /**
     * 如果 RedissonClient 实例存在，测使用 redis 进行 GuoywSession 存储 以实现分布式服务的Session 同步
     */
    if (redissonClient != null){
      RMapCache<Serializable, Session> rMap = redissonClient.getMapCache(properties.getRedissonStoreName());
      RMapCache<Serializable, Session> rMapCache = redissonClient.getMapCache(properties.getRedissonStoreName() + "-cache");
      EnterpriseCacheSessionDAO sessionDAO = new RedissonSessionDAO(rMap);
      sessionManager.setSessionDAO(sessionDAO);
      sessionManager.setCacheManager(new AbstractCacheManager(){
        @Override
        protected Cache createCache(String s) throws CacheException{
          return new RedissonSessionCache(rMapCache);
        }
      });
    }
    return sessionManager;
  }
  
  @Override
  public Class<?> getObjectType(){
    return SessionManager.class;
  }
}
