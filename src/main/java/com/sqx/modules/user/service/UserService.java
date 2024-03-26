package com.sqx.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqx.common.utils.Result;
import com.sqx.modules.user.entity.UserEntity;

public interface UserService extends IService<UserEntity> {

    Result insertUser(UserEntity userEntity);

    Result userLogin(String account, String password);

    /**
     * 返回登录token
     *
     * @param user 用户信息
     * @return
     */
    Result getResult(UserEntity user);

    UserEntity getUserById(Long id);

    UserEntity getUserByAccount(String account);

    Result updateUser(UserEntity userEntity);


}