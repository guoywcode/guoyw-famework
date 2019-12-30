package com.guoyw.framework.base.support_id.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.guoyw.framework.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

/**
 * @author: guoyw
 * create: 2019-12-23 09:32
 **/
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class IdBaseEntity extends BaseEntity{
  
  @Id
  @ApiModelProperty(value = "ID-唯一标识")
  @Column(length = 20,updatable = false, nullable = false)
  private String id;

}
