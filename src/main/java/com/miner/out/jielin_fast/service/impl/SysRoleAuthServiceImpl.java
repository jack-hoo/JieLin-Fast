package com.miner.out.jielin_fast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.out.jielin_fast.dao.SysRoleAuthDao;
import com.miner.out.jielin_fast.entity.SysRoleAuthEntity;
import com.miner.out.jielin_fast.service.SysRoleAuthService;



@Service("sysRoleAuthService")
public class SysRoleAuthServiceImpl implements SysRoleAuthService {
	@Autowired
	private SysRoleAuthDao sysRoleAuthDao;
	
	@Override
	public SysRoleAuthEntity queryObject(Integer id){
		return sysRoleAuthDao.queryObject(id);
	}
	
	@Override
	public List<SysRoleAuthEntity> queryList(Map<String, Object> map){
		return sysRoleAuthDao.queryList(map);
	}

	@Override
	public int saveBatch(List<SysRoleAuthEntity> roleAuthEntities) {
		return sysRoleAuthDao.saveBatch(roleAuthEntities);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return sysRoleAuthDao.queryTotal(map);
	}
	
	@Override
	public void save(SysRoleAuthEntity sysRoleAuth){
		sysRoleAuthDao.save(sysRoleAuth);
	}
	
	@Override
	public void update(SysRoleAuthEntity sysRoleAuth){
		sysRoleAuthDao.update(sysRoleAuth);
	}
	
	@Override
	public void delete(Integer id){
		sysRoleAuthDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sysRoleAuthDao.deleteBatch(ids);
	}
	
}
