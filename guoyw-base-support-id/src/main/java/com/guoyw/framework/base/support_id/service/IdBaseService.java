package com.guoyw.framework.base.support_id.service;

import com.guoyw.base.service.BaseService;
import com.guoyw.framework.base.support_id.entity.IdBaseEntity;
import com.guoyw.utils.SnowFlake;
import com.guoyw.utils.SpringBeanUtilsExt;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author: guoyw
 * create: 2019-12-23 10:53
 **/
public interface IdBaseService< E extends IdBaseEntity> extends BaseService<E,String>{
  
  
  @Transactional
  default E updateByIdIgnoreNull(E entity) {
    E target = this.genFreeEntityFromDbById(entity);
    SpringBeanUtilsExt.copyPropertiesIgnoreNull(entity, target);
    return update(target);
  }
  
  @Transactional
  default E updateByIdIgnoreEmpty(E entity) {
    E target = this.genFreeEntityFromDbById(entity);
    SpringBeanUtilsExt.copyPropertiesIgnoreEmpty(entity, target);
    return update(target);
  }
  
  default E genFreeEntityFromDbById(E entity) {
    E target = this.newEntity(entity);
    E source = this.getById(entity.getId());
    SpringBeanUtilsExt.copyProperties(source, target);
    return target;
  }
  
  @Override
  default E beforeCreate(E entity){
    BaseService.super.beforeCreate(entity);
    if(entity.getId() == null || entity.getId().equals(0))
      entity.setId(String.valueOf(SnowFlake.getInstance().nextId()));
    return entity;
  }
  
}
