package com.sqx.modules.chat.controller;

import com.sqx.common.utils.Result;
import com.sqx.modules.app.annotation.Login;
import com.sqx.modules.chat.entity.ChatMsg;
import com.sqx.modules.chat.service.ChatMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "私信聊天信息", tags = {"私信聊天信息"})
@RequestMapping(value = "/chatMsg")
public class ChatController {

    @Autowired
    ChatMsgService chatMsgService;

    @Login
    @RequestMapping(value = "/getMsgByUserId", method = RequestMethod.GET)
    @ApiOperation("根据用户id获取他的所有聊天记录")
    @ResponseBody
    public Result getMsgByUserId(@ApiParam(value = "当前用户id", required = true) @RequestParam(required = true, value = "userId") Integer userId) {
        Result result = chatMsgService.getMsgByUserId(userId);
        return result;
    }

    @Login
    @RequestMapping(value = "/getMsgByTwoUserId", method = RequestMethod.GET)
    @ApiOperation("根据两个用户id获取两个用户之间的聊天记录")
    @ResponseBody
    public Result getMsgByTwoUserId(@RequestHeader("token") String token,
                                    @ApiParam(value = "发送者的用户id", required = true) @RequestParam(required = true, value = "sendUserid") Integer userId1,
                                    @ApiParam(value = "接收者的用户id", required = true) @RequestParam(required = true, value = "reciveUserid") Integer userId2) {
        Result result = chatMsgService.getMsgByTwoUserId(userId1, userId2);
        return result;
    }

    @Login
    @RequestMapping(value = "/getLatestChatMsg", method = RequestMethod.GET)
    @ApiOperation("获取当前用户最新的聊天信息")
    @ResponseBody
    public Result getLatestChatMsg(@ApiParam(value = "当前登录用户的用户id", required = true) @RequestParam(required = true, value = "userId") Integer userId) {
        Result result = chatMsgService.getLatestChatMsg(userId);
        return result;
    }

    @Login
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    @ApiOperation("发送私信聊天信息")
    @ResponseBody
    public Result sendMsg(@RequestBody ChatMsg chatMsg) {
        Result result = chatMsgService.sendMsg(chatMsg);
        return result;
    }
}
