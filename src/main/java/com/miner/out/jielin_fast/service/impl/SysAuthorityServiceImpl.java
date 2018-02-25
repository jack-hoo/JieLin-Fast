package com.miner.out.jielin_fast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.out.jielin_fast.dao.SysAuthorityDao;
import com.miner.out.jielin_fast.entity.SysAuthorityEntity;
import com.miner.out.jielin_fast.service.SysAuthorityService;



@Service("sysAuthorityService")
public class SysAuthorityServiceImpl implements SysAuthorityService {
	@Autowired
	private SysAuthorityDao sysAuthorityDao;

	@Override
	public List<SysAuthorityEntity> getAuthesByRoleId(Integer roleId) {
		return sysAuthorityDao.findAuthsByRoleId(roleId);
	}

	@Override
	public SysAuthorityEntity queryObject(Integer id){
		return sysAuthorityDao.queryObject(id);
	}
	
	@Override
	public List<SysAuthorityEntity> queryList(Map<String, Object> map){
		return sysAuthorityDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysAuthorityDao.queryTotal(map);
	}
	
	@Override
	public void save(SysAuthorityEntity sysAuthority){
		sysAuthorityDao.save(sysAuthority);
	}
	
	@Override
	public void update(SysAuthorityEntity sysAuthority){
		sysAuthorityDao.update(sysAuthority);
	}
	
	@Override
	public void delete(Integer id){
		sysAuthorityDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sysAuthorityDao.deleteBatch(ids);
	}
	
}
