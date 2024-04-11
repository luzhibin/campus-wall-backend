package com.sqx.modules.chat.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqx.common.utils.Result;
import com.sqx.modules.chat.dao.ChatMsgDao;
import com.sqx.modules.chat.entity.ChatMsg;
import com.sqx.modules.chat.service.ChatMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service("chatMsgService")
@Slf4j
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgDao, ChatMsg> implements ChatMsgService {

    @Autowired
    ChatMsgDao chatMsgDao;

    @Override
    public Result getMsgByUserId(Integer userId) {

        return null;
    }

    @Override
    public Result getMsgByTwoUserId(Integer userId1, Integer userId2) {
        List<ChatMsg> msgByTwoUserId = chatMsgDao.getMsgByTwoUserId(userId1, userId2);

        return Result.success().put("code", 200).put("data", msgByTwoUserId);
    }

}
