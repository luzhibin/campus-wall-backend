package com.sqx.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sqx.common.utils.PageUtils;
import com.sqx.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
