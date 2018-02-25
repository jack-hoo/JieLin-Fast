/**
 * Copyright (C), 2008-2018, 杭州迪火科技有限公司
 * FileName: SysUserDTO
 * Author:   shugan
 * Date:     2018/2/25 13:36
 * Description: 用户Dto
 */
package com.miner.out.jielin_fast.dto;

import com.miner.out.jielin_fast.common.validator.group.Register;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 〈用户Dto〉
 *
 * @author shugan
 * @create 2018/2/25
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class SysUserDTO {
    //用户账号(只能是字母或者特殊字符)
    @NotNull(message = "登录账号不能为空",groups = {Register.class})
    private String account;
    //密码(符合一定规则)
    @NotNull(message = "登录密码不能为空",groups = {Register.class})
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$" ,message = "密码由6-16位数字和字母的组合")
    private String password;
    //用户名称
    private String username;
    //性别
    private Integer sex;
    //邮箱
    private String email;
    //电话
    private String phone;
    //微信号
    private String wechatId;

    private String qq;
}