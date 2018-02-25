package com.miner.out.jielin_fast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.miner.out.jielin_fast.dao.SysRoleDao;
import com.miner.out.jielin_fast.entity.SysRoleEntity;
import com.miner.out.jielin_fast.service.SysRoleService;



@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Override
	public SysRoleEntity queryObject(Integer id){
		return sysRoleDao.queryObject(id);
	}
	
	@Override
	public List<SysRoleEntity> queryList(Map<String, Object> map){
		return sysRoleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysRoleDao.queryTotal(map);
	}
	
	@Override
	public void save(SysRoleEntity sysRole){
		sysRoleDao.save(sysRole);
	}
	
	@Override
	public void update(SysRoleEntity sysRole){
		sysRoleDao.update(sysRole);
	}
	
	@Override
	public void delete(Integer id){
		sysRoleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sysRoleDao.deleteBatch(ids);
	}
	
}
