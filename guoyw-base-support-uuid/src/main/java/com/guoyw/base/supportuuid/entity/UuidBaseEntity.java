package com.guoyw.base.supportuuid.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.guoyw.base.supportid.entity.IdBaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;


/**
 * @author: guoyw
 * create: 2019-12-23 09:32
 **/
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class UuidBaseEntity extends IdBaseEntity{
  
  @ApiModelProperty(value = "UUID-全局唯一标识", example = "0")
  @Column(length = 32,updatable = false)
  private String uuid;

}
