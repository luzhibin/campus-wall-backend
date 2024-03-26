package com.sqx.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqx.common.utils.PageUtils;
import com.sqx.modules.sys.entity.SysDictEntity;

/**
 * 数据字典
 *
 */
public interface SysDictService extends IService<SysDictEntity> {

    PageUtils queryPage(Integer page,Integer limit,String name,String parentId,String type,Integer status);
}

