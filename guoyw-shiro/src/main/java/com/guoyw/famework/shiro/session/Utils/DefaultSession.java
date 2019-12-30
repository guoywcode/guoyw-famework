package com.guoyw.famework.shiro.session.Utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class DefaultSession extends AbstractGuoywSession {
  @Override
  protected Session getSessionObject() {
    return SecurityUtils.getSubject().getSession();
  }
}
