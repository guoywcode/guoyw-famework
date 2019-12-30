package com.guoyw.famework.shiro.persistence;

import java.lang.reflect.Method;

public interface RolePersistenceBean{
  void persistenceRole(Class controllerClass, Method method);
}
