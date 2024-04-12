package com.sqx.modules.comment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqx.modules.comment.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {

}
