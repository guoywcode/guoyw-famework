package com.guoyw.framework.test;
import com.guoyw.framework.starter.shiro.EnableGuoywShiro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableGuoywApiVersion
@EnableGuoywShiro
public class GuoywFrameworkTestApplication{

  public static void main(String[] args){
    SpringApplication.run(GuoywFrameworkTestApplication.class, args);
  }

}
