package com.sqx.modules.posts.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqx.common.utils.Result;
import com.sqx.modules.posts.entity.PostCollectEntity;
import com.sqx.modules.posts.entity.PostLikeEntity;
import com.sqx.modules.posts.entity.PostsEntity;

public interface PostsService extends IService<PostsEntity> {

    /**
     * 查询帖子列表，不分页
     * @return
     */
    Result getPostList();

    /**
     * 查询帖子列表，带分页
     * @return
     */
    Result getPostListPage(String token, Integer page, Integer limit);

    /**
     * 根据帖子id获取帖子
     * @param postsId
     * @return
     */
    Result getPostsById(Integer postsId);

    /**
     * 根据用户账号获取帖子(分页)
     * @param account
     * @return
     */
    Result getPostsByUserAccount(String account);

    /**
     * 新增帖子
     * @param postsEntity
     * @return
     */
    Result insertPost(String token, PostsEntity postsEntity);

    /**
     * 更新帖子
     * @param postsEntity
     * @return
     */
    Result updatePost(PostsEntity postsEntity);

    /**
     * 删除帖子
     * @param postsEntity
     * @return
     */
    Result deletePost(PostsEntity postsEntity);

    /**
     * 帖子点赞
     * @param postsLikeEntity
     * @return
     */
    Result postLike(PostLikeEntity postsLikeEntity);

    /**
     * 帖子收藏
     * @param postCollectEntity
     * @return
     */
    Result postCollect(PostCollectEntity postCollectEntity);

    /**
     * 根据帖子id查询点赞的用户列表
     * @param postId
     * @return
     */
    Result queryLikeList(Integer postId);

    /**
     * 根据帖子id查询收藏帖子的用户列表
     * @param postId
     * @return
     */
    Result queryCollectList(Integer postId);


}
