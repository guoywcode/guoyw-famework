package com.guoyw.framework.base.exception;

/**
 * @author: guoyw
 * created: 2019-12-27 18:36
 */
public class GuoywException extends RuntimeException{
  
  private String[] params;
  
  public GuoywException(String codeName) {
    super(codeName);
    this.params = new String[]{};
  }
  
  public GuoywException(String codeName, String[] params) {
    super(codeName);
    this.params = params;
  }
  
  public GuoywException(String codeName, String[] params, Throwable cause) {
    super(codeName, cause);
    this.params = params;
  }
  
  public String[] getParams() {
    return params;
  }
}
