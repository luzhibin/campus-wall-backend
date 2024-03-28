package com.sqx.modules.posts.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqx.common.utils.PageUtils;
import com.sqx.common.utils.Result;
import com.sqx.modules.posts.entity.PostsEntity;

public interface PostsService extends IService<PostsEntity> {

    /**
     * 查询帖子列表，不分页
     * @return
     */
    Result getPostsList();

    /**
     * 查询帖子列表，带分页
     * @return
     */
    PageUtils getPostsListPage();

    /**
     * 根据帖子id获取帖子
     * @param postsId
     * @return
     */
    Result getPostsById(Integer postsId);

    /**
     * 根据用户id获取帖子
     * @param userId
     * @return
     */
    Result getPostsByUserId(Integer userId);

    /**
     * 根据用户账号获取帖子
     * @param account
     * @return
     */
    Result getPostsByUserAccount(String account);

    /**
     * 新增帖子
     * @param postsEntity
     * @return
     */
    Result insertPosts(PostsEntity postsEntity);

    /**
     * 更新帖子
     * @param postsEntity
     * @return
     */
    Result updatePosts(PostsEntity postsEntity);

    /**
     * 删除帖子
     * @param postsEntity
     * @return
     */
    Result deletePosts(PostsEntity postsEntity);
}
