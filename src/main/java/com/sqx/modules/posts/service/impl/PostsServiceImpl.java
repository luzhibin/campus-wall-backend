package com.sqx.modules.posts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqx.common.utils.PageUtils;
import com.sqx.common.utils.Result;
import com.sqx.modules.posts.dao.PostLikeDao;
import com.sqx.modules.posts.dao.PostsDao;
import com.sqx.modules.posts.entity.PostLikeEntity;
import com.sqx.modules.posts.entity.PostsEntity;
import com.sqx.modules.posts.service.PostsService;
import com.sqx.modules.user.entity.UserEntity;
import com.sqx.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("postsService")
@Slf4j
public class PostsServiceImpl extends ServiceImpl<PostsDao, PostsEntity> implements PostsService {

    @Autowired
    PostsDao postsDao;
    @Autowired
    UserService userService;
    @Autowired
    PostLikeDao postsLikeDao;


    @Override
    public Result getPostList() {
        QueryWrapper<PostsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0).orderByAsc("create_time");
        List<PostsEntity> postsEntities = postsDao.selectList(queryWrapper);
        return Result.success().put("code", 200).put("data", postsEntities);
    }

    @Override
    public Result getPostListPage(Integer page, Integer limit) {
        IPage<PostsEntity> iPage = new Page<>(page, limit);
        QueryWrapper<PostsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0).orderByAsc("create_time");
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
        queryWrapper.eq("user_id", userId).eq("is_delete", 0);
        List<PostsEntity> postsEntities = postsDao.selectList(queryWrapper);
        return Result.success().put("code", 200).put("data", postsEntities);
    }

    @Override
    public Result insertPost(PostsEntity postsEntity) {
        Date date = new Date();
        postsEntity.setCreateTime(date);
        postsEntity.setLikeCount(0);
        postsEntity.setCollectCount(0);
        postsEntity.setIsDelete(false);
        postsDao.insert(postsEntity);
        return Result.success().put("code", 200).put("data", "新增帖子成功！");
    }

    @Override
    public Result updatePost(PostsEntity postsEntity) {
        postsEntity.setUpdateTime(new Date());
        postsDao.updateById(postsEntity);
        return Result.success().put("code", 200).put("data", "更新帖子成功！");
    }

    @Override
    public Result deletePost(PostsEntity postsEntity) {
        postsEntity.setUpdateTime(new Date());
        postsEntity.setIsDelete(true);
        postsDao.updateById(postsEntity);
        return Result.success().put("code", 200).put("data", "删除帖子成功！");
    }

    @Override
    @Transactional
    public Result postLike(PostLikeEntity postsLikeEntity) {
        Integer postId = postsLikeEntity.getPostId();
        PostsEntity postsEntity = postsDao.selectById(postId);
        if (postsEntity.getLikeCount() == null){
            postsEntity.setLikeCount(1);
        }else {
            postsEntity.setLikeCount(postsEntity.getLikeCount() + 1);
        }
        postsDao.updateById(postsEntity);

        postsLikeDao.insert(postsLikeEntity);
        return Result.success().put("code", 200).put("data", "点赞帖子成功！");
    }
}
