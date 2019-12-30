package com.guoyw.famework.shiro.session.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.session.Session;
import org.redisson.api.RMapCache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: guoyw
 * create: 2019-12-30 11:48
 **/

public class RedissonSessionCache implements Cache<Serializable, Session>{
  
  private final RMapCache<Serializable, Session> map;
  
  public RedissonSessionCache(RMapCache<Serializable, Session> map) {
    this.map = map;
  }
  
  public RMapCache<Serializable, Session> getMap() {
    return map;
  }
  
  @Override
  public Session get(Serializable key) throws CacheException{
    return map.get(key);
  }
  
  @Override
  public Session put(Serializable key, Session session) throws CacheException {
    // ttl 只所以要设置为 GuoywSession 过期时间的 5 倍，
    // 是为了保证 在启用 SessionValidationScheduler 时，
    // GuoywSession 可以被 SessionValidationScheduler 清理掉；
    // 并且在没有启用 SessionValidationScheduler 时，
    // 也可以自动过期由 redis 清理掉过期 GuoywSession ；
    return map.put(key, session, session.getTimeout() * 5, TimeUnit.MILLISECONDS);
  }
  
  @Override
  public Session remove(Serializable key) throws CacheException {
    return map.remove(key);
  }
  
  @Override
  public void clear() throws CacheException {
    map.clear();
  }
  
  @Override
  public int size() {
    return map.size();
  }
  
  @Override
  public Set<Serializable> keys() {
    return map.keySet();
  }
  
  @Override
  public Collection<Session> values() {
    return map.values();
  }
}
