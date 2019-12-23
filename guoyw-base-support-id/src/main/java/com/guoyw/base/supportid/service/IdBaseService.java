package com.guoyw.base.supportid.service;

import com.guoyw.base.service.BaseService;
import com.guoyw.base.supportid.entity.IdBaseEntity;
import com.guoyw.utils.SnowFlake;

/**
 * @author: guoyw
 * create: 2019-12-23 10:53
 **/
public interface IdBaseService< E extends IdBaseEntity> extends BaseService<E,Long>{
  
  @Override
  default E beforeCreate(E entity){
    BaseService.super.beforeCreate(entity);
    if(entity.getId() == null || entity.getId().equals(0))
      entity.setId(SnowFlake.getInstance().nextId());
    return entity;
  }
  
}
