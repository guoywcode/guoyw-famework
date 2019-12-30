package com.guoyw.famework.shiro.persistence;

import java.lang.reflect.Method;

public interface PermissionPersistenceBean{
  void persistencePermission(Class controllerClass, Method method);
}
