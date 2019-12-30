package com.guoyw.framework.base.support_id.controller;
import com.guoyw.framework.base.controller.BaseController;
import com.guoyw.framework.base.support_id.entity.IdBaseEntity;
import com.guoyw.framework.base.support_id.service.IdBaseService;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: guoyw
 * create: 2019-12-24 09:26
 **/
@RestController
@ApiModel("基础接口，每个继承该类的控制器都有的接口")
public abstract class IdBaseController<E extends IdBaseEntity> extends BaseController<E ,String>{
  protected abstract IdBaseService<E> getService();
}
