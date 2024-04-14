package com.sqx.modules.chat.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqx.common.utils.Result;
import com.sqx.modules.chat.dao.ChatMsgDao;
import com.sqx.modules.chat.entity.ChatMsg;
import com.sqx.modules.chat.service.ChatMsgService;
import com.sqx.modules.user.dao.UserDao;
import com.sqx.modules.user.entity.UserEntity;
import com.sqx.modules.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service("chatMsgService")
@Slf4j
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgDao, ChatMsg> implements ChatMsgService {

    @Autowired
    ChatMsgDao chatMsgDao;
    @Autowired
    UserDao userDao;

    @Override
    public Result getMsgByUserId(Integer userId) {

        List<ChatMsg> chatMsgList = chatMsgDao.getMsgByUserId(userId);
        return Result.success().put("code", 200).put("data", chatMsgList);
    }

    @Override
    public Result getMsgByTwoUserId(Integer userId1, Integer userId2) {
        List<ChatMsg> msgByTwoUserId = chatMsgDao.getMsgByTwoUserId(userId1, userId2);

        return Result.success().put("code", 200).put("data", msgByTwoUserId);
    }

    @Override
    public Result getLatestChatMsg(Integer userId) {
        QueryWrapper<ChatMsg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("recive_userid", userId).orderByDesc("sendtime").last("limit 1");
        ChatMsg chatMsg = chatMsgDao.selectOne(queryWrapper);
        return Result.success().put("code", 200).put("data", chatMsg);
    }

    @Override
    @Transactional
    public Result sendMsg(ChatMsg chatMsg) {
        Integer sendUserId = chatMsg.getSendUserid();
        Integer reciveUserId = chatMsg.getReciveUserid();
        if (Objects.isNull(chatMsg.getSendUsername()) || StringUtils.isNullOrEmpty(chatMsg.getSendUsername())) {
            UserEntity sendUser = userDao.selectById(sendUserId);
            chatMsg.setSendUsername(sendUser.getAccount());
        }
        if (Objects.isNull(chatMsg.getSendUserAvatar()) || StringUtils.isNullOrEmpty(chatMsg.getSendUserAvatar())) {
            UserEntity sendUser = userDao.selectById(sendUserId);
            chatMsg.setSendUserAvatar(sendUser.getAvatar());
        }
        if (Objects.isNull(chatMsg.getReciveUsername()) || StringUtils.isNullOrEmpty(chatMsg.getReciveUsername())) {
            UserEntity reciveUser = userDao.selectById(reciveUserId);
            chatMsg.setReciveUsername(reciveUser.getAccount());
        }
        if (Objects.isNull(chatMsg.getReciveUserAvatar()) || StringUtils.isNullOrEmpty(chatMsg.getReciveUserAvatar())) {
            UserEntity reciveUser = userDao.selectById(reciveUserId);
            chatMsg.setReciveUserAvatar(reciveUser.getAvatar());
        }
        if (Objects.isNull(chatMsg.getMsgtype())){
            chatMsg.setMsgtype(0);
        }
        chatMsg.setSendtime(new Date());
        chatMsgDao.insert(chatMsg);
        return Result.success().put("code", 200).put("data", chatMsg);
    }



}
