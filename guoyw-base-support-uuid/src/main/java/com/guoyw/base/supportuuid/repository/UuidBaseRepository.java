package com.guoyw.base.supportuuid.repository;
import com.guoyw.base.supportid.repository.IdBaseRepository;
import com.guoyw.base.supportuuid.entity.UuidBaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;

/**
 * @author: guoyw
 * create: 2019-12-23 10:51
 **/
@NoRepositoryBean
public interface UuidBaseRepository<E extends UuidBaseEntity> extends IdBaseRepository<E>{
  
  E findByUuid(String uuid);
  
  @Transactional
  void deleteByUuid(String uuid);
  
}
