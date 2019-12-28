package com.guoyw.framework.base.dto;

import java.io.Serializable;

/**
 * @author: guoyw
 * create: 2019-12-09 18:59
 **/
public interface DTOConvert<S,T> extends Serializable{
  T convertTo();
  S convertFor(T t);
}
