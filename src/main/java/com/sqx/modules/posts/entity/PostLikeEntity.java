package com.sqx.modules.posts.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**实体类
 * 用户关联帖子点赞
 */
@Data
@ApiModel("用户关联帖子点赞")
@TableName("post_like")
public class PostLikeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 帖子主键id
     */
    @ApiModelProperty("用户关联帖子点赞 关联id")
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

}
