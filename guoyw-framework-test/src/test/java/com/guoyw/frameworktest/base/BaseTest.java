package com.guoyw.frameworktest.base;
import com.guoyw.framework.test.base.entity.User;
import com.guoyw.framework.test.base.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: guoyw
 * create: 2019-12-23 14:31
 **/
@SpringBootTest
public class BaseTest{
  
  @Autowired
  UserService userService;
  
  @Test
  public void create(){
    User user = new User();
    user.setIdName("张三")
      .setPosition("研发");
    userService.create(user);
  }
  
  @Test
  public void getAll(){
    List<User> user = userService.getAll();
    System.out.println(user);
  }
  
}
