package com.guoyw.frameworktest.base.service;
import com.guoyw.base.supportuuid.repository.UuidBaseRepository;
import com.guoyw.base.supportuuid.service.UuidBaseService;
import com.guoyw.frameworktest.base.entity.User;
import com.guoyw.frameworktest.base.reository.UserRepository;
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
