package com.guoyw.famework.shiro.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoleAndPermissionPersistenceBeanPostProcessor implements BeanPostProcessor {


  public void execPermissionPersistence(PermissionPersistenceBean permissionPersistenceBean) {
    if (!permissionPersistenceInfoList.isEmpty()) {
      for (PersistenceInfo info : permissionPersistenceInfoList) {
        permissionPersistenceBean.persistencePermission(info.getBeanClass(), info.getMethod());
      }
      this.permissionPersistenceInfoList.clear();
    }
  }

  public void execRolePersistence(RolePersistenceBean rolePersistenceBean) {
    if (!rolePersistenceInfoList.isEmpty()) {
      for (PersistenceInfo info : rolePersistenceInfoList) {
        rolePersistenceBean.persistenceRole(info.getBeanClass(), info.getMethod());
      }
      this.rolePersistenceInfoList.clear();
    }
  }

  List<PersistenceInfo> rolePersistenceInfoList = new ArrayList<>();
  List<PersistenceInfo> permissionPersistenceInfoList = new ArrayList<>();

  public Object postProcessAfterInitialization(Object bean, String beanName) {
    Class beanClass = bean.getClass();
    if (beanClass.getName().contains("$$EnhancerBySpringCGLIB$$")) {
      beanClass = beanClass.getSuperclass();
    }
    String beanClassName = beanClass.getName();
    if (beanClass.getAnnotation(Controller.class) != null
      || beanClass.getAnnotation(RestController.class) != null) {
      log.info("Shiro Persistence process Controller：" + beanClassName);
      for (Method method : beanClass.getMethods()) {
        if (method.getAnnotation(RequiresPermissions.class) != null) {
          permissionPersistenceInfoList.add(new PersistenceInfo(beanClass, method));
        }
        if (method.getAnnotation(RequiresRoles.class) != null) {
          rolePersistenceInfoList.add(new PersistenceInfo(beanClass, method));
        }
      }
    }
    return bean;
  }

  @Getter
  @Setter
  private static class PersistenceInfo {
    private Class beanClass;
    private Method method;

    public PersistenceInfo(Class beanClass, Method method) {
      this.beanClass = beanClass;
      this.method = method;
    }
  }

}
