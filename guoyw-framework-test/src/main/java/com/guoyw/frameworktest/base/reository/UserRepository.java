package com.guoyw.frameworktest.base.reository;

import com.guoyw.base.supportuuid.repository.UuidBaseRepository;
import com.guoyw.frameworktest.base.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author: guoyw
 * create: 2019-12-18 11:51
 **/
@Repository
public interface UserRepository extends UuidBaseRepository<User>{
}
