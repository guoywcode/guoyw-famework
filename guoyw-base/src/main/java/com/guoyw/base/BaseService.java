package com.guoyw.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author: guoyw
 * create: 2019-12-20 16:16
 **/

public interface BaseService< E extends BaseEntity, ID>{
  @Autowired
  BaseRepository<E,ID> getRepository();
  
  
  default E create(E entity) {
    entity = beforeCreate(entity);
    return getRepository().save(entity);
  }
  
  default E update(E entity) {
    entity = beforeUpdate(entity);
    return getRepository().save(entity);
  }
  
  default E saveAndFlush(E entity) {
    return getRepository().saveAndFlush(entity);
  }
  
  default void delete(E entity) {
    getRepository().delete(entity);
  }
  
  default long count() {
    return getRepository().count();
  }
  
  default long count(Specification<E> specification) {
    return getRepository().count(specification);
  }
  
  default List<E> findAll() {
    return getRepository().findAll();
  }
  
  default Page<E> findAll(Pageable pageable){
    return getRepository().findAll(pageable);
  }
  
  default Page<E> findAll(Specification<E> specification, Pageable pageable) {
    return getRepository().findAll(specification, pageable);
  }
  
  default E beforeCreate(E entity) {
    long timestamp = System.currentTimeMillis();
    if (entity.getCreatedAt() == null) {
      entity.setCreatedAt(timestamp);
    }
    if (entity.getUpdatedAt() == null) {
      entity.setUpdatedAt(timestamp);
    }
    return entity;
  }
  
  default E beforeUpdate(E entity) {
    long timestamp = System.currentTimeMillis();
    if (entity.getUpdatedAt() == null) {
      entity.setUpdatedAt(timestamp);
    }
    return entity;
  }
  
}
