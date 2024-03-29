package com.sqx.modules.posts.controller;

import com.sqx.common.utils.Result;
import com.sqx.modules.posts.entity.PostsEntity;
import com.sqx.modules.posts.service.PostsService;
import com.sqx.modules.user.entity.UserEntity;
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

    @RequestMapping(value = "/getPostsList", method = RequestMethod.GET)
    @ApiOperation("获取帖子列表")
    @ResponseBody
    public Result getPostsList() {
        Result result = postsService.getPostsList();
        return result;
    }

    @RequestMapping(value = "/getPostsListPage", method = RequestMethod.GET)
    @ApiOperation("获取帖子列表(分页)")
    @ResponseBody
    public Result getPostsListPage(@RequestParam(required = true, value = "page") Integer page,
                                   @RequestParam(required = true, value = "limit") Integer limit) {
        Result result = postsService.getPostsListPage(page, limit);
        return result;
    }

    @RequestMapping(value = "/addPosts", method = RequestMethod.POST)
    @ApiOperation("发帖")
    @ResponseBody
    public Result addPosts(@RequestBody PostsEntity postsEntity) {
        Result result = postsService.insertPosts(postsEntity);
        return result;
    }

    @RequestMapping(value = "/updatePosts", method = RequestMethod.POST)
    @ApiOperation("更新帖子")
    @ResponseBody
    public Result updatePosts(@RequestBody PostsEntity postsEntity) {
        Result result = postsService.updatePosts(postsEntity);
        return result;
    }

    @RequestMapping(value = "/deletePosts", method = RequestMethod.POST)
    @ApiOperation("删除帖子")
    @ResponseBody
    public Result deletePosts(@RequestBody PostsEntity postsEntity) {
        Result result = postsService.deletePosts(postsEntity);
        return result;
    }
}
