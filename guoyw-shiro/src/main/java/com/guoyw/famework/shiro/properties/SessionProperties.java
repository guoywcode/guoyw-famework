package com.guoyw.famework.shiro.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author: guoyw
 * create: 2019-12-30 11:25
 **/
@Data
@ConfigurationProperties("guoyw.session")
public class SessionProperties implements Serializable{
  
  private String tokenIdentification = "SHIRO-TOKEN";
  
  private long timeout = 1800000;
  
  // 必须小于 timeout 的四倍
  private long sessionValidationInterval = 1800000;
  
  private String redissonStoreName = "GUOYW-SHIRO-SESSION-REDISSON-STORE";
  
  private String loginTokenName = "SHIRO-SESSION-LOGIN-TOKEN";
  
}
