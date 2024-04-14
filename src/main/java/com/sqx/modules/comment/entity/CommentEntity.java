package com.sqx.modules.comment.entity;

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
 * 用户评论帖子
 */
@Data
@ApiModel("帖子评论")
@TableName("post_comment")
public class CommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户评论帖子的主键id
     */
    @ApiModelProperty(value = "主键id，添加评论时不用传这个参数，后端自动生成", example = "1")
    @TableId(type = IdType.AUTO,value = "comment_id")
    private Integer commentId;

    /**
     * 帖子id
     */
    @ApiModelProperty(value = "帖子id,number类型", example = "1")
    @TableField("post_id")
    private Integer postId;

    /**
     * 该帖子用户的用户id
     */
    @ApiModelProperty("该帖子用户的用户id,number类型")
    @TableField("user_id")
    private Integer userId;

    /**
     * 发送评论的用户id
     */
    @ApiModelProperty("发送评论的用户id,number类型")
    @TableField("send_user_id")
    private Integer sendUserId;

    /**
     * 评论的内容
     */
    @ApiModelProperty("评论的内容, string类型")
    @TableField("comment_text")
    private Integer commentText;

    /**
     * 评论时间
     */
    @ApiModelProperty("评论时间")
    @TableField("comment_time")
    private Date commentTime;

    /**
     * 是否删除评论 1删除
     */
    @ApiModelProperty("是否删除评论 1删除")
    @TableField("is_delete")
    private Boolean isDelete;

}
