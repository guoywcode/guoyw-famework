package com.guoyw.base.service;

import com.guoyw.base.entity.BaseEntity;
import com.guoyw.base.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
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
  
  default Iterable<E> createAll(Iterable<E> entities) {
    for (E e : entities) {
      e = beforeCreate(e);
    }
    return getRepository().saveAll(entities);
  }
  
  default E update(E entity) {
    entity = beforeUpdate(entity);
    return getRepository().save(entity);
  }
  
  default Iterable<E> updateAll(Iterable<E> entities) {
    for (E e : entities) {
      e = beforeUpdate(e);
    }
    return getRepository().saveAll(entities);
  }
  
  default E saveAndFlush(E entity) {
    return getRepository().saveAndFlush(entity);
  }
  
  default void flush() {
    getRepository().flush();
  }
  
  default void remove(E entity) {
    getRepository().delete(entity);
  }
  
  @Transactional
  default void removeById(ID id) {
    E entity = this.getById(id);
    getRepository().deleteById(id);
  }
  
  default long countAll() {
    return getRepository().count();
  }
  
  default long count(Specification<E> specification) {
    return getRepository().count(specification);
  }
  
  default List<E> getAll() {
    return getRepository().findAll();
  }
  
  default Page<E> getPage(Pageable pageable){
    return getRepository().findAll(pageable);
  }
  
  default Page<E> getPage(Specification<E> specification, Pageable pageable) {
    return getRepository().findAll(specification, pageable);
  }
  
  default E getById(ID id) {
    return getRepository().findById(id).orElse(null);
  }
  
  default List<E> getByIds(Iterable<ID> ids) {
    return getRepository().findAllById(ids);
  }
  
  default List<E> getAllByCreatedAtDesc() {
    return getRepository().findAllByOrderByCreatedAtDesc();
  }
  
  default List<E> getAllByCreatedAt() {
    return getRepository().findAllByOrderByCreatedAtAsc();
  }
  
  default E beforeCreate(E entity) {
    long timestamp = System.currentTimeMillis();
    if (entity.getCreatedAt() == null || entity.getCreatedAt().equals(0)) {
      entity.setCreatedAt(timestamp);
    }
    if (entity.getUpdatedAt() == null || entity.getCreatedAt().equals(0)) {
      entity.setUpdatedAt(timestamp);
    }
    return entity;
  }
  
  default E beforeUpdate(E entity) {
    long timestamp = System.currentTimeMillis();
    if (entity.getUpdatedAt() == null || entity.getUpdatedAt().equals(0) ) {
      entity.setUpdatedAt(timestamp);
    }
    return entity;
  }
  
  default E newEntity(E entity) {
    try {
      Object object = entity.getClass().newInstance();
      if (entity.getClass().isInstance(object)) {
        entity = (E) object;
      }
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Generate Entity Exception", e);
    }
    return entity;
  }
  
}
