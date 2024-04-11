package com.sqx.modules.posts.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqx.modules.posts.entity.PostsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostsDao extends BaseMapper<PostsEntity> {

}
