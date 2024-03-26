package com.sqx.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据字典
 *
 */
@Data
@TableName("sys_dict")
public class SysDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;
	/**
	 * 字典名称
	 */

	private String name;
	/**
	 * 字典类型
	 */

	private String type;

	/**
	 * 上级id 0是顶级
	 */
	private Long parentId;

	/**
	 * 字典码
	 */

	private String code;
	/**
	 * 字典值
	 */

	private String value;
	/**
	 * 排序
	 */
	private Integer orderNum;
	/**
	 * 备注
	 */
	private String remark;

	private Integer status;


}
