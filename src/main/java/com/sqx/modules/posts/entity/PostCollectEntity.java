package com.sqx.modules.posts.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**实体类
 * 用户关联帖子收藏
 */
@Data
@ApiModel("用户关联帖子收藏")
@TableName("post_collect")
public class PostCollectEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子主键id
     */
    @ApiModelProperty("用户关联帖子收藏 关联id")
    @TableId(type = IdType.AUTO,value = "post_user_id")
    private Integer postUserId;

    /**
     * 帖子id
     */
    @ApiModelProperty("帖子id")
    @TableField("post_id")
    private Integer postId;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Integer userId;

    /**
     * 收藏时间
     */
    @ApiModelProperty("收藏时间")
    @TableField("collect_time")
    private Date collectTime;

}
