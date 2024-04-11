package com.sqx.modules.chat.controller;

import com.sqx.common.utils.Result;
import com.sqx.modules.app.annotation.Login;
import com.sqx.modules.chat.service.ChatMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public Result getMsgByUserId() {

        return null;
    }

    @Login
    @RequestMapping(value = "/getMsgByTwoUserId", method = RequestMethod.GET)
    @ApiOperation("根据两个用户id获取两个用户之间的聊天记录")
    @ResponseBody
    public Result getMsgByTwoUserId(@RequestHeader("token") String token,
                                    @RequestParam(required = true, value = "userId1") Integer userId1,
                                    @RequestParam(required = true, value = "userId2") Integer userId2) {
        Result result = chatMsgService.getMsgByTwoUserId(userId1, userId2);
        return result;
    }
}
