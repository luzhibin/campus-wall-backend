package com.sqx.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqx.common.utils.Result;
import com.sqx.modules.app.utils.JwtUtils;
import com.sqx.modules.sys.entity.SysUserEntity;
import com.sqx.modules.sys.service.SysUserService;
import com.sqx.modules.sys.service.SysUserTokenService;
import com.sqx.modules.user.dao.UserDao;
import com.sqx.modules.user.entity.UserEntity;
import com.sqx.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Override
    public Result insertUser(UserEntity userEntity) {
        String account = userEntity.getAccount();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        Integer i = userDao.selectCount(queryWrapper);
        if (i > 0){
            Result.success().put("code", 500).put("data", "该账号已存在！");
        }
        String hexPassword = DigestUtils.sha256Hex(userEntity.getPassword());
        userEntity.setPassword(hexPassword);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userEntity.setCreateTime(sdf.format(new Date()));
        userEntity.setUpdateTime(sdf.format(new Date()));
        userDao.insert(userEntity);
        return Result.success().put("code", 200).put("data", "用户注册成功！");
    }

    @Override
    public Result userLogin(String account, String password) {
        UserEntity user = this.getUserByAccount(account);
        if (Objects.isNull(user) || user.getUserId() == null){
            Result.success().put("code", 500).put("data", "该账号不存在！");
        }
        String hexPwd = DigestUtils.sha256Hex(password);
        if (!hexPwd.equals(user.getPassword())){
            Result.success().put("code", 500).put("data", "密码错误，请重新输入！");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setUpdateTime(sdf.format(new Date()));
        userDao.updateById(user);
        Result r = sysUserTokenService.createToken(user.getUserId());
        r.put("data", user);
        return r;
    }

    @Override
    public Result getResult(UserEntity user) {
        //生成token
        String token = jwtUtils.generateToken(user.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        map.put("user", user);
        return Result.success(map);
    }

    @Override
    public UserEntity getUserById(Long id) {
        UserEntity userEntity = baseMapper.selectById(id);
        return userEntity;
    }

    @Override
    public UserEntity getUserByAccount(String account) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        UserEntity userEntity = userDao.selectOne(queryWrapper);
        return userEntity;
    }

    @Override
    public Result updateUser(UserEntity userEntity) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userEntity.setUpdateTime(sdf.format(new Date()));
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", userEntity.getAccount());
        userDao.update(userEntity, queryWrapper);
        return Result.success().put("code", 200).put("data", userEntity);
    }

}
