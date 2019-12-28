package com.guoyw.framework.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author: guoyw
 * create: 2019-12-20 15:58
 **/
@Data
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class BaseEntity implements Serializable{
  
  @CreatedDate
  @Column(unique = false,updatable = false)
  @ApiModelProperty(value = "创建时间戳", example = "0")
  private Long createdAt;
  
  @LastModifiedDate
  @Column(unique = false)
  @ApiModelProperty(value = "更新时间戳", example = "0")
  private Long updatedAt;
  
}
