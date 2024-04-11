package com.sqx.modules.posts.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
     * 用户头像
     */
    @TableField(exist = false)
    @JsonProperty("userPic")
    private String userPic;

    /**
     * 用户名
     */
    @TableField(exist = false)
    @JsonProperty("username")
    private String username;

    /**
     * 性别
     */
    @TableField(exist = false)
    @JsonProperty("sex")
    private Integer sex;

    /**
     * 年龄
     */
    @TableField(exist = false)
    @JsonProperty("age")
    private Integer age;

    /**
     *  帖子标题
     */
    @JsonProperty("title")
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
    @JsonIgnore
    @TableField("image1")
    private String image1;

    /**
     *  图片2
     */
    @JsonIgnore
    @TableField("image2")
    private String image2;

    /**
     *  图片3
     */
    @JsonIgnore
    @TableField("image3")
    private String image3;

    @TableField(exist = false)
    private String[] imageArray;

    /**
     *  封面图
     */
    @TableField("cover_pic")
    @JsonProperty("coverPic")
    private String coverPic;

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
     *  评论数
     */
    @ApiModelProperty("点赞数")
    @TableField("comment_num")
    @JsonProperty("commentNum")
    private Integer commentNum;

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

    /**
     * 是否是发布者，发布者可以删除，则为1，不可删除0
     */
    @ApiModelProperty("是否可删除")
    @TableField("can_del")
    private Boolean canDel;

}
