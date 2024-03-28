package com.sqx.modules.posts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqx.common.utils.PageUtils;
import com.sqx.common.utils.Result;
import com.sqx.modules.posts.dao.PostsDao;
import com.sqx.modules.posts.entity.PostsEntity;
import com.sqx.modules.posts.service.PostsService;
import com.sqx.modules.user.entity.UserEntity;
import com.sqx.modules.user.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("postsService")
@Slf4j
public class PostsServiceImpl extends ServiceImpl<PostsDao, PostsEntity> implements PostsService {

    @Autowired
    PostsDao postsDao;
    @Autowired
    UserService userService;

    @Override
    public Result getPostsList() {
        QueryWrapper<PostsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("create_time");
        List<PostsEntity> postsEntities = postsDao.selectList(queryWrapper);
        return Result.success().put("code", 200).put("data", postsEntities);
    }

    @Override
    public Result getPostsListPage(Integer page, Integer limit) {
        IPage<PostsEntity> iPage = new Page<>(page, limit);
        QueryWrapper<PostsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("create_time");
        IPage<PostsEntity> postsEntityIPage = postsDao.selectPage(iPage, queryWrapper);
        PageUtils result = new PageUtils(postsEntityIPage);
        return Result.success().put("code", 200).put("data", result);
    }


    @Override
    public Result getPostsById(Integer postsId) {
        PostsEntity postsEntity = baseMapper.selectById(postsId);
        return Result.success().put("code", 200).put("data", postsEntity);
    }

    @Override
    public Result getPostsByUserAccount(String account) {
        UserEntity user = userService.getUserByAccount(account);
        Long userId = user.getUserId();
        QueryWrapper<PostsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<PostsEntity> postsEntities = postsDao.selectList(queryWrapper);
        return Result.success().put("code", 200).put("data", postsEntities);
    }

    @Override
    public Result insertPosts(PostsEntity postsEntity) {
        Date date = new Date();
        postsEntity.setCreateTime(date);
        postsEntity.setLikeCount(0);
        postsEntity.setCollectCount(0);
        postsDao.insert(postsEntity);
        return Result.success().put("code", 200).put("data", "新增帖子成功！");
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
