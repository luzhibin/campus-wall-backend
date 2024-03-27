package com.sqx.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqx.common.utils.Result;
import com.sqx.modules.user.entity.UserEntity;
import com.sqx.modules.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "用户管理", tags = {"用户管理"})
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    @ApiOperation("用户注册")
    @ResponseBody
    public Result userRegister(@RequestBody UserEntity userEntity) {
        Result result = userService.insertUser(userEntity);
        return result;
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ApiOperation("用户登录")
    @ResponseBody
    public Result userLogin(@RequestParam(name = "account", required = true) String account,
                            @RequestParam(name = "password", required = true) String password) {
        Result result = userService.userLogin(account, password);
        return result;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ApiOperation("根据用户ID获取用户信息")
    @ResponseBody
    public Result selectUserById(@ApiParam("用户id") @PathVariable Long userId) {
        UserEntity userEntity = userService.getUserById(userId);
        return Result.success().put("code", 200).put("data", userEntity);
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.GET)
    @ApiOperation("根据账号查询用户信息")
    @ResponseBody
    public Result selectUserById(@ApiParam("账号") @PathVariable String account) {
        UserEntity userEntity = userService.getUserByAccount(account);
        return Result.success().put("code", 200).put("data", userEntity);
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ApiOperation("修改用户信息")
    @ResponseBody
    public Result selectUserById(@RequestBody UserEntity userEntity) {
        Result result = userService.updateUser(userEntity);
        return result;
    }

}
