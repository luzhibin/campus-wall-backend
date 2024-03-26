package com.sqx.modules.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqx.modules.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
