package com.sqx.modules.posts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqx.common.utils.PageUtils;
import com.sqx.common.utils.Result;
import com.sqx.modules.posts.dao.PostsDao;
import com.sqx.modules.posts.entity.PostsEntity;
import com.sqx.modules.posts.service.PostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postsService")
@Slf4j
public class PostsServiceImpl extends ServiceImpl<PostsDao, PostsEntity> implements PostsService {

    @Autowired
    PostsDao postsDao;

    @Override
    public Result getPostsList() {
        QueryWrapper<PostsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("create_time");
        List<PostsEntity> postsEntities = postsDao.selectList(queryWrapper);
        return Result.success().put("code", 200).put("data", postsEntities);
    }

    @Override
    public PageUtils getPostsListPage() {
        return null;
    }

    @Override
    public Result getPostsById(Integer postsId) {
        return null;
    }

    @Override
    public Result getPostsByUserId(Integer userId) {
        return null;
    }

    @Override
    public Result getPostsByUserAccount(String account) {
        return null;
    }

    @Override
    public Result insertPosts(PostsEntity postsEntity) {
        return null;
    }

    @Override
    public Result updatePosts(PostsEntity postsEntity) {
        return null;
    }

    @Override
    public Result deletePosts(PostsEntity postsEntity) {
        return null;
    }
}
