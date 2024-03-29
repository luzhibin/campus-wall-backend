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

/**
 * 帖子实体类
 */
@Data
@ApiModel("帖子")
@TableName("posts")
public class PostsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 帖子主键id
     */
    @ApiModelProperty("帖子id")
    @TableId(type = IdType.AUTO,value = "posts_id")
    private Integer postsId;


    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Integer userId;

    /**
     *  帖子标题
     */
    @ApiModelProperty("帖子标题")
    @TableField("title")
    private String title;

    /**
     *  帖子内容
     */
    @ApiModelProperty("帖子内容")
    @TableField("content")
    private String content;

    /**
     *  图片1
     */
    @ApiModelProperty("图片1")
    @TableField("image1")
    private String image1;

    /**
     *  图片2
     */
    @ApiModelProperty("图片2")
    @TableField("image2")
    private String image2;

    /**
     *  图片3
     */
    @ApiModelProperty("图片3")
    @TableField("image3")
    private String image3;

    /**
     *  创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    /**
     *  更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;

    /**
     *  点赞数
     */
    @ApiModelProperty("点赞数")
    @TableField("like_count")
    private Integer likeCount;

    /**
     *  收藏数
     */
    @ApiModelProperty("收藏数")
    @TableField("collect_count")
    private Integer collectCount;

    /**
     *  是否删除 1删除 0启用
     */
    @ApiModelProperty("是否删除")
    @TableField("is_delete")
    private Boolean isDelete;

}
