package com.guoyw.base.supportuuid.service;

import com.guoyw.base.supportid.service.IdBaseService;
import com.guoyw.base.supportuuid.entity.UuidBaseEntity;
import com.guoyw.base.supportuuid.repository.UuidBaseRepository;
import com.guoyw.utils.SpringBeanUtilsExt;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * @author: guoyw
 * create: 2019-12-23 10:53
 **/
public interface UuidBaseService< E extends UuidBaseEntity> extends IdBaseService<E>{
  
  @Override
  UuidBaseRepository<E> getRepository();
  
  default E getByUuid(String uuid) {
    return getRepository().findByUuid(uuid);
  }
  
  @Transactional
  default E removeByUuid(String uuid) {
    E entity = getByUuid(uuid);
    getRepository().deleteByUuid(uuid);
    return entity;
  }
  
  @Transactional
  default E updateByUuidIgnoreNull(E entity) {
    E target = this.genFreeEntityFromDbByUuid(entity);
    SpringBeanUtilsExt.copyPropertiesIgnoreNull(entity, target);
    return update(target);
  }
  
  @Transactional
  default E updateByUuidIgnoreEmpty(E entity) {
    E target = this.genFreeEntityFromDbByUuid(entity);
    SpringBeanUtilsExt.copyPropertiesIgnoreEmpty(entity, target);
    return update(target);
  }
  
  @Override
  default E beforeCreate(E entity){
    IdBaseService.super.beforeCreate(entity);
    if(entity.getUuid() == null || entity.getUuid().isEmpty())
      entity.setUuid(UUID.randomUUID().toString().replace("-",""));
    return entity;
  }
  
  default E genFreeEntityFromDbByUuid(E entity) {
    E target = this.newEntity(entity);
    E source = this.getByUuid(entity.getUuid());
    SpringBeanUtilsExt.copyProperties(source, target);
    return target;
  }
}
