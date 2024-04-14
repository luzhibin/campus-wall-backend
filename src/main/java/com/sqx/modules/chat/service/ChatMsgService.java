package com.sqx.modules.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqx.common.utils.Result;
import com.sqx.modules.chat.entity.ChatMsg;

public interface ChatMsgService extends IService<ChatMsg> {

    Result getMsgByUserId(Integer userId);

    Result getMsgByTwoUserId(Integer userId1, Integer userId2);

    Result getLatestChatMsg(Integer userId);

    Result sendMsg(ChatMsg chatMsg);
}
