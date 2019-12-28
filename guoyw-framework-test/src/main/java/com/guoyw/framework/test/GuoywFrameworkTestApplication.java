package com.guoyw.framework.test;

import com.guoyw.framework.starter.api_versioning.EnableGuoywApiVersion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableGuoywApiVersion
public class GuoywFrameworkTestApplication{

  public static void main(String[] args){
    SpringApplication.run(GuoywFrameworkTestApplication.class, args);
  }

}
