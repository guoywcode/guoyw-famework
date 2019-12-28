package com.guoyw.framework.base.dto;
import com.guoyw.utils.SpringBeanUtilsExt;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: guoyw
 * create: 2019-12-09 20:22
 **/
public abstract class BaseDTO<S, T> implements DTOConvert<S, T>{
  @Override
  public T convertTo(){
    Type[] p = getTypes();
    T t = (T) getInstance((Class) p[1]);
    SpringBeanUtilsExt.copyPropertiesIgnoreNull(this, t);
    return t;
  }
  
  @Override
  public S convertFor(T t){
    Type[] p = getTypes();
    S s = (S) getInstance((Class) p[0]);
    SpringBeanUtilsExt.copyPropertiesIgnoreNull(t, s);
    return s;
  }
  
  private Object getInstance(Class clazz){
    try{
      
      return clazz.newInstance();
    }catch(InstantiationException e){
      e.printStackTrace();
    }catch(IllegalAccessException e){
      e.printStackTrace();
    }
    return null;
  }
  
  private Type[] getTypes(){
    Class clazz = getClass();
    Type type = clazz.getGenericSuperclass();
    while(!(type instanceof ParameterizedType)){
      clazz = clazz.getSuperclass();
      type = clazz.getGenericSuperclass();
    }
    Type[] p = ((ParameterizedType) type).getActualTypeArguments();
    return p;
  }
}
