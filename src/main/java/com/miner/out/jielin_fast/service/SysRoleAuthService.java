package com.miner.out.jielin_fast.service;

import com.miner.out.jielin_fast.entity.SysRoleAuthEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-25 15:08:59
 */
public interface SysRoleAuthService {
	
	SysRoleAuthEntity queryObject(Integer id);
	
	List<SysRoleAuthEntity> queryList(Map<String, Object> map);

	int saveBatch(List<SysRoleAuthEntity> roleAuthEntities);

	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleAuthEntity sysRoleAuth);
	
	void update(SysRoleAuthEntity sysRoleAuth);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
