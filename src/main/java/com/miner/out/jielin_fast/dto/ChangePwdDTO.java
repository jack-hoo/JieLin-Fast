/**
 * Copyright (C), 2008-2017, 杭州迪火科技有限公司
 * FileName: ChangePwdDTO
 * Author:   shugan
 * Date:     2017/12/20 19:02
 * Description: 修改密码模型
 */
package com.miner.out.jielin_fast.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 〈修改密码模型〉
 *
 * @author shugan
 * @create 2017/12/20
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class ChangePwdDTO {
    @NotNull(message = "登录账号不能为空")
    private String account;
    @NotNull(message = "原始密码不能为空")
    private String oldPwd;
    @NotNull(message = "新密码不能为空")
    private String newPwd;
}