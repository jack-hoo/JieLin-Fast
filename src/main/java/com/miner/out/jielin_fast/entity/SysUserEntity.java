package com.miner.out.jielin_fast.entity;

import java.io.Serializable;
import java.util.Date;

import com.miner.out.jielin_fast.common.validator.group.Register;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-24 22:25:10
 */
@Data
@Accessors(chain = true)
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户账号(只能是字母或者特殊字符)
    //用户登录账号

	private String account;
	//密码(符合一定规则)

	private String password;
	//用户名称
	private String username;
	//
	private Integer sex;
	//
	private String email;

	private String qq;
	//
	private String phone;
	//
	private String wechatId;
	//
	private Integer roleId;
	//
	private Integer status;
	//
	private Date createTime;

}
