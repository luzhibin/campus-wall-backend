package com.sqx.modules.chat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqx.modules.chat.entity.ChatMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMsgDao extends BaseMapper<ChatMsg> {

    List<ChatMsg> getMsgByTwoUserId(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);
}
