package com.sqx.modules.posts.controller;

import com.sqx.common.utils.Result;
import com.sqx.modules.app.annotation.Login;
import com.sqx.modules.posts.entity.PostLikeEntity;
import com.sqx.modules.posts.entity.PostsEntity;
import com.sqx.modules.posts.service.PostsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "帖子管理", tags = {"帖子管理"})
@RequestMapping(value = "/posts")
public class PostsController {

    @Autowired
    PostsService postsService;

    @Login
    @RequestMapping(value = "/getPostList", method = RequestMethod.GET)
    @ApiOperation("获取帖子列表")
    @ResponseBody
    public Result getPostList() {
        Result result = postsService.getPostList();
        return result;
    }

    @Login
    @RequestMapping(value = "/getPostListPage", method = RequestMethod.GET)
    @ApiOperation("获取帖子列表(分页)")
    @ResponseBody
    public Result getPostListPage(@RequestParam(required = true, value = "page") Integer page,
                                   @RequestParam(required = true, value = "limit") Integer limit) {
        Result result = postsService.getPostListPage(page, limit);
        return result;
    }

    @Login
    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    @ApiOperation("发帖")
    @ResponseBody
    public Result addPost(@RequestBody PostsEntity postsEntity) {
        Result result = postsService.insertPost(postsEntity);
        return result;
    }

    @Login
    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    @ApiOperation("更新帖子")
    @ResponseBody
    public Result updatePost(@RequestBody PostsEntity postsEntity) {
        Result result = postsService.updatePost(postsEntity);
        return result;
    }

    @Login
    @RequestMapping(value = "/deletePost", method = RequestMethod.POST)
    @ApiOperation("删除帖子")
    @ResponseBody
    public Result deletePost(@RequestBody PostsEntity postsEntity) {
        Result result = postsService.deletePost(postsEntity);
        return result;
    }

    @Login
    @RequestMapping(value = "/postLike", method = RequestMethod.POST)
    @ApiOperation("帖子点赞")
    @ResponseBody
    public Result postLike(@RequestBody PostLikeEntity postLikeEntity) {
        Result result = postsService.postLike(postLikeEntity);
        return result;
    }

    @Login
    @RequestMapping(value = "/queryLikeList", method = RequestMethod.GET)
    @ApiOperation("根据帖子id查询点赞的用户列表")
    @ResponseBody
    public Result queryLikeList(@RequestParam(value = "postId", required = true) Integer postId) {
        Result result = postsService.queryLikeList(postId);
        return result;
    }


}
