package com.guoyw.framework.test.base.reository;

import com.guoyw.framework.base.support_uuid.repository.UuidBaseRepository;
import com.guoyw.framework.test.base.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author: guoyw
 * create: 2019-12-18 11:51
 **/
@Repository
public interface UserRepository extends UuidBaseRepository<User>{
}
