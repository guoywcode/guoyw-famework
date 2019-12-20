package com.guoyw.frameworktest.base.entity;

import com.guoyw.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author: guoyw
 * create: 2019-12-20 11:36
 **/
@Entity
@Table(name = "user")
@Data
public class User extends BaseEntity{
  
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column()
  @ApiModelProperty("id")
  private long id;
  
  @ApiModelProperty("用户名称")
  @Column(length = 25)
  private String idName;
  
  @ApiModelProperty("用户职位")
  @Column(length = 50)
  private String position;
  
}
