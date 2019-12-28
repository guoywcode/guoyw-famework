package com.guoyw.framework.test.base.entity;

import com.guoyw.framework.base.support_uuid.entity.UuidBaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author: guoyw
 * create: 2019-12-20 11:36
 **/
@Entity
@Table(name = "user")
@Data
@Accessors(chain = true)
public class User extends UuidBaseEntity{
  
  @ApiModelProperty("用户名称")
  @Column(length = 25)
  private String idName;
  
  @ApiModelProperty("用户职位")
  @Column(length = 50)
  private String position;
  
}
