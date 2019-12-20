package com.guoyw.frameworktest.base.service;

import com.guoyw.base.BaseRepository;
import com.guoyw.base.BaseService;
import com.guoyw.frameworktest.base.entity.User;
import com.guoyw.frameworktest.base.reository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: guoyw
 * create: 2019-12-20 16:49
 **/
@Service
public class UserService implements BaseService<User,Long>{
  
  @Autowired
  private UserRepository userRepository;
  
  @Override
  public BaseRepository<User, Long> getRepository(){
    return userRepository;
  }
  
}
