package com.guoyw.framework.base.controller;

import com.guoyw.framework.base.entity.BaseEntity;
import com.guoyw.framework.base.service.BaseService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author: guoyw
 * create: 2019-12-24 09:14
 **/
@RestController
@ApiModel("基础接口，每个继承该类的控制器都有的接口")
public abstract class BaseController<E extends BaseEntity,ID>{
  
  protected abstract BaseService<E, ID> getService();
  
  @PostMapping
  @ApiOperation("添加")
  public E add(@RequestBody E e) {
    return getService().create(e);
  }
  
  @PutMapping
  @ApiOperation("编辑")
  public E edit(@RequestBody E e) {
    return getService().update(e);
  }
  
  @DeleteMapping(path = "{id}")
  @ApiOperation("删除")
  public E remove(@PathVariable("id") ID id) {
    E e = getService().getById(id);
    getService().removeById(id);
    return e;
  }
  
  @GetMapping(path = "{id}")
  @ApiOperation("详情")
  public E details(@PathVariable("id") ID id) {
    return getService().getById(id);
  }
}
