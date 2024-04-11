package com.sqx.modules.posts.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqx.common.utils.Result;
import com.sqx.modules.posts.entity.PostLikeEntity;

public interface PostLikeService extends IService<PostLikeEntity> {

    Result userLikesPost(Integer postId, Integer userId);
}
