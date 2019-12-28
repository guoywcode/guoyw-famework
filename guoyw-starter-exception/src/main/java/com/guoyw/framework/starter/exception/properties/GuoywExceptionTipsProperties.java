package com.guoyw.framework.starter.exception.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("guoyw.exception.tips")
public class GuoywExceptionTipsProperties{
  private String codeName = "codeName";
  private String msgName = "msgName";
}
