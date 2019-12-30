package com.guoyw.framework.base.support_id.repository;
import com.guoyw.framework.base.repository.BaseRepository;
import com.guoyw.framework.base.support_id.entity.IdBaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author: guoyw
 * create: 2019-12-23 10:51
 **/
@NoRepositoryBean
public interface IdBaseRepository<E extends IdBaseEntity> extends BaseRepository<E, String>{

}
