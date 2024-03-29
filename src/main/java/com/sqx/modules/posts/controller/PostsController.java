package com.sqx.modules.posts.controller;

import com.sqx.common.utils.Result;
import com.sqx.modules.posts.entity.PostsEntity;
import com.sqx.modules.posts.entity.PostsLikeEntity;
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

    @RequestMapping(value = "/getPostList", method = RequestMethod.GET)
    @ApiOperation("获取帖子列表")
    @ResponseBody
    public Result getPostList() {
        Result result = postsService.getPostList();
        return result;
    }

    @RequestMapping(value = "/getPostListPage", method = RequestMethod.GET)
    @ApiOperation("获取帖子列表(分页)")
    @ResponseBody
    public Result getPostListPage(@RequestParam(required = true, value = "page") Integer page,
                                   @RequestParam(required = true, value = "limit") Integer limit) {
        Result result = postsService.getPostListPage(page, limit);
        return result;
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    @ApiOperation("发帖")
    @ResponseBody
    public Result addPost(@RequestBody PostsEntity postsEntity) {
        Result result = postsService.insertPost(postsEntity);
        return result;
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    @ApiOperation("更新帖子")
    @ResponseBody
    public Result updatePost(@RequestBody PostsEntity postsEntity) {
        Result result = postsService.updatePost(postsEntity);
        return result;
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.POST)
    @ApiOperation("删除帖子")
    @ResponseBody
    public Result deletePost(@RequestBody PostsEntity postsEntity) {
        Result result = postsService.deletePost(postsEntity);
        return result;
    }

    @RequestMapping(value = "/postLike", method = RequestMethod.POST)
    @ApiOperation("帖子点赞")
    @ResponseBody
    public Result postLike(@RequestBody PostsLikeEntity postsLikeEntity) {
        Result result = postsService.deletePosts(postsEntity);
        return result;
    }
}
