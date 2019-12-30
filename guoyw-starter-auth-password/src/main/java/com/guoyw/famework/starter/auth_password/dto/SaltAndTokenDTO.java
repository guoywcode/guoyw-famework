package com.guoyw.famework.starter.auth_password.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class SaltAndTokenDTO implements Serializable {
  private String salt;

  private String token;

  private String onlineIp;

  public SaltAndTokenDTO() {
  }

  public SaltAndTokenDTO(String salt, String token) {
    this.salt = salt;
    this.token = token;
  }

  public SaltAndTokenDTO(String salt, String token, String onlineIp) {
    this.salt = salt;
    this.token = token;
    this.onlineIp = onlineIp;
  }
}
