package com.sqx.modules.chat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@ApiModel("聊天信息对象")
@TableName("chat_msg")
public class ChatMsg {

    /**
     * 消息主键id
     */
    @ApiModelProperty(value = "消息主键id，唯一约束, num类型", example = "1")
    @TableId(type = IdType.AUTO,value = "msg_id")
    private Integer msgId;


    /**
     * 发送者id
     */
    @ApiModelProperty(value = "发送者的用户id, num类型", example = "1")
    @TableField("send_userid")
    private String sendUserid;

    /**
     * 接收者id
     */
    @ApiModelProperty(value = "接收者的用户id, num类型", example = "1")
    @TableField("recive_userid")
    private String reciveUserid;

    /**
     * 发送内容
     */
    @ApiModelProperty(value = "发送内容, string类型", example = "")
    @TableField("sendtext")
    private String sendtext;

    /**
     * 发送时间
     */
    @ApiModelProperty("发送时间（可不传，后端自动根据当前时间生成）")
    @TableField("sendtime")
    private Date sendtime;

    /**
     * 消息类型 0文本消息，1图片消息，2视频影像
     */
    @ApiModelProperty("消息类型 0文本消息，1图片消息，2视频影像")
    @TableField("msgtype")
    private String msgtype;
}