package com.sqx.modules.posts.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqx.common.utils.Result;
import com.sqx.modules.posts.dao.PostLikeDao;
import com.sqx.modules.posts.entity.PostLikeEntity;
import com.sqx.modules.posts.service.PostLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("postLikeService")
@Slf4j
public class PostLikeServiceImpl extends ServiceImpl<PostLikeDao, PostLikeEntity> implements PostLikeService {

    @Autowired
    PostLikeDao postLikeDao;

    @Override
    public Result userLikesPost(Integer postId, Integer userId) {
        PostLikeEntity postLikeEntity = new PostLikeEntity();
        postLikeEntity.setPostId(postId);
        postLikeEntity.setUserId(userId);
        postLikeDao.insert(postLikeEntity);
        return Result.success().put("code", 200);
    }
}
