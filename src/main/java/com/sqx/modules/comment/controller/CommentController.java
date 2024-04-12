package com.sqx.modules.comment.controller;

import com.sqx.common.utils.Result;
import com.sqx.modules.app.annotation.Login;
import com.sqx.modules.comment.entity.CommentEntity;
import com.sqx.modules.comment.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "帖子评论", tags = {"帖子评论"})
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Login
    @RequestMapping(value = "/getCommentByPostId", method = RequestMethod.GET)
    @ApiOperation("根据帖子id获取该帖子的评论")
    @ResponseBody
    public Result getCommentByPostId(@RequestParam(value = "postId", required = true)Integer postId) {
        Result result = commentService.getCommentByPostId(postId);
        return result;
    }

    @Login
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ApiOperation("添加帖子评论")
    @ResponseBody
    public Result addComment(@RequestBody CommentEntity commentEntity) {
        Result result = commentService.addComment(commentEntity);
        return result;
    }

    @Login
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    @ApiOperation("删除帖子评论")
    @ResponseBody
    public Result deleteComment(@RequestParam(value = "commentId") Integer commentId) {
        Result result = commentService.deleteComment(commentId);
        return result;
    }

}
