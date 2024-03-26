package com.sqx.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 用户实体类
 */
@Data
@ApiModel("用户")
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户id")
	@TableId(type = IdType.AUTO,value = "user_id")
	private Long userId;

	/**
	 * 用户名
	 */
	@ApiModelProperty("账号")
	@TableField("account")
	private String account;

	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	@TableField("password")
	private String password;

	/**
	 * 性别 1男 2女
	 */
	@ApiModelProperty("性别 1男 2女")
	@TableField("sex")
	private Integer sex;

	/**
	 * 年龄
	 */
	@ApiModelProperty("年龄")
	@TableField("age")
	private Integer age;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	@TableField("create_time")
	private String createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	@TableField("update_time")
	private String updateTime;
}
