package com.guoyw.base.supportid.repository;
import com.guoyw.base.repository.BaseRepository;
import com.guoyw.base.supportid.entity.IdBaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author: guoyw
 * create: 2019-12-23 10:51
 **/
@NoRepositoryBean
public interface IdBaseRepository<E extends IdBaseEntity> extends BaseRepository<E, Long>{

}
