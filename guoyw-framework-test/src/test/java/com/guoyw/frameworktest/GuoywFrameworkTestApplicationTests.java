package com.guoyw.frameworktest;

import com.guoyw.frameworktest.base.entity.User;
import com.guoyw.frameworktest.base.reository.UserRepository;
import com.guoyw.frameworktest.base.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuoywFrameworkTestApplicationTests{

  @Autowired
  UserService userService;
  
  @Test
  public void contextLoads(){
    User user = new User();
    user.setIdName("zhangsan1");
    userService.create(user);
  }

}
