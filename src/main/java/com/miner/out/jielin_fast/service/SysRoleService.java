package com.miner.out.jielin_fast.service;

import com.miner.out.jielin_fast.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-25 15:08:59
 */
public interface SysRoleService {
	
	SysRoleEntity queryObject(Integer id);
	
	List<SysRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleEntity sysRole);
	
	void update(SysRoleEntity sysRole);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
