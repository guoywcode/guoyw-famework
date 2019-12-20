package com.guoyw.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: guoyw
 * create: 2019-12-20 16:07
 **/
@Repository
public interface BaseRepository<E, ID> extends JpaRepository<E, ID>, CrudRepository<E, ID>, JpaSpecificationExecutor<E>{
}
