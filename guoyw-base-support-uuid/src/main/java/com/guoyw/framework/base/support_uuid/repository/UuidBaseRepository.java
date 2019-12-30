package com.guoyw.framework.base.support_uuid.repository;
import com.guoyw.framework.base.support_id.repository.IdBaseRepository;
import com.guoyw.framework.base.support_uuid.entity.UuidBaseEntity;
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
