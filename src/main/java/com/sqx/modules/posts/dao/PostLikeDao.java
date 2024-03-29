package com.sqx.modules.posts.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqx.modules.posts.entity.PostLikeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostLikeDao extends BaseMapper<PostLikeEntity> {

}
