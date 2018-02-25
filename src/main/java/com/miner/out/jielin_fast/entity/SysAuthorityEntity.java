package com.miner.out.jielin_fast.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-25 15:08:59
 */
@Data
@Accessors(chain = true)
public class SysAuthorityEntity implements Serializable,GrantedAuthority {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//权限代码
	private String authCode;
	//权限名称
	private String authName;
	//接口地址
	private String url;
	//请求方法类型:1 get,2 post,3 input,4 delete
	private Integer method;
	//资源类型：1接口资源，2服务资源
	private Integer resourceType;

	private Date createTime;
	@Override
	public String getAuthority() {
		return this.authCode;
	}
}
