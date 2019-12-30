package com.guoyw.famework.starter.auth_password.controller;

import com.guoyw.famework.starter.auth_password.dto.SaltAndTokenDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: guoyw
 * create: 2019-12-30 09:59
 **/
@RestController
@RequestMapping("/api/yioks/auth")
public class GuoywPasswordAuthController{
  
  @ApiOperation("登录前，用于获取盐和token的接口")
  @GetMapping("salt/{username}")
  public SaltAndTokenDTO getSalt(@PathVariable("username") String username) {
    return passwordAuthBiz.getSalt(username);
  }
  
  @ApiOperation("检查登录状态的接口")
  @GetMapping("/checkLogin")
  @RequiresAuthentication
  public YioksLoginResult checkLogin() {
    return passwordAuthBiz.checkLogin();
  }
  
}
