/**
 * Copyright (C), 2008-2018, 杭州迪火科技有限公司
 * FileName: AuthController
 * Author:   shugan
 * Date:     2018/2/25 13:19
 * Description: 认证授权控制器
 */
package com.miner.out.jielin_fast.controller;

import com.miner.out.jielin_fast.common.constant.AccountStatus;
import com.miner.out.jielin_fast.common.constant.ExceptionEnum;
import com.miner.out.jielin_fast.common.utils.Result;
import com.miner.out.jielin_fast.common.utils.ResultUtil;
import com.miner.out.jielin_fast.common.validator.group.Register;
import com.miner.out.jielin_fast.dto.ChangePwdDTO;
import com.miner.out.jielin_fast.dto.SysUserDTO;
import com.miner.out.jielin_fast.entity.SysUserEntity;
import com.miner.out.jielin_fast.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈认证授权控制器〉
 *
 * @author shugan
 * @create 2018/2/25
 * @since 1.0.0
 */
@Api(value = "用户认证相关接口")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private SysUserService userService;

    @ApiOperation(value = "用户注册", response = Result.class)
    @PostMapping("/register")
    public Result register(@RequestBody @Validated({Register.class}) SysUserDTO user) {
        return ResultUtil.success(userService.registerUser(user, 1, AccountStatus.ENABLED_ACCOUNT));
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", response = Result.class)
    public Result login(@RequestBody @Validated({Register.class}) SysUserDTO userDTO) {
        String token = userService.login(userDTO.getAccount(), userDTO.getPassword());
        SysUserEntity userByAccount = null;
        if (!StringUtils.isEmpty(token)) {

            userByAccount = userService.findUserByAccount(userDTO.getAccount());
        }
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("role", userByAccount.getRoleId());
        userInfo.put("name", userByAccount.getUsername());
        userInfo.put("account", userByAccount.getAccount());

        //返回用户信息
        result.put("token", token);
        result.put("userInfo",userInfo);
        return ResultUtil.success(result);
    }

    @PostMapping("/change_pwd")
    @ApiOperation(value = "修改密码", response = Result.class)
    public Result changePassword(@RequestBody @Validated ChangePwdDTO changePwdDTO) {
        //判断用户是否存在，且密码是否正确
        userService.login(changePwdDTO.getAccount(), changePwdDTO.getOldPwd());
        SysUserEntity user = userService.findUserByAccount(changePwdDTO.getAccount());
        user.setPassword(changePwdDTO.getNewPwd());
        userService.update(user);
        return ResultUtil.success();
    }

    @PostMapping("/reset_pwd/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "重置密码", response = Result.class)
    public Result resetPwd(@PathVariable(value = "username", required = true) String account) {
        SysUserEntity user = userService.findUserByAccount(account);
        if (user == null) {
            return ResultUtil.error(ExceptionEnum.USER_NOT_EXIST);
        }
        user.setPassword("888888");
        userService.update(user);
        return ResultUtil.success();
    }
}