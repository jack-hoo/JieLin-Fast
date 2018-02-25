package com.miner.out.jielin_fast.service;

import com.miner.out.jielin_fast.common.constant.AccountStatus;
import com.miner.out.jielin_fast.dto.SysUserDTO;
import com.miner.out.jielin_fast.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-24 22:25:10
 */
public interface SysUserService {

	String login(String username, String password);

	SysUserEntity findUserByAccount(String username);

	SysUserEntity registerUser(SysUserDTO userDTO, int role, AccountStatus status);

	SysUserEntity queryObject(Integer id);
	
	List<SysUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysUserEntity sysUser);
	
	void update(SysUserEntity sysUser);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
