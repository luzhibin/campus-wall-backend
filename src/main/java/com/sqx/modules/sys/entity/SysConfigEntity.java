package com.sqx.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 系统配置信息
 *
 */
@Data
@TableName("sys_config")
public class SysConfigEntity {
	@TableId
	private Long id;
	private String paramKey;
	private String paramValue;
	private String remark;

}
