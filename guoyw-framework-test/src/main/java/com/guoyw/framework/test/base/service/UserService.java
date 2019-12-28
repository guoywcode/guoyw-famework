package com.guoyw.framework.test.base.service;
import com.guoyw.framework.base.support_uuid.repository.UuidBaseRepository;
import com.guoyw.framework.base.support_uuid.service.UuidBaseService;
import com.guoyw.framework.test.base.entity.User;
import com.guoyw.framework.test.base.reository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: guoyw
 * create: 2019-12-20 16:49
 **/
@Service
public class UserService implements UuidBaseService<User>{
  
  @Autowired
  private UserRepository userRepository;
  
  
  @Override
  public UuidBaseRepository<User> getRepository(){
    return userRepository;
  }
}
