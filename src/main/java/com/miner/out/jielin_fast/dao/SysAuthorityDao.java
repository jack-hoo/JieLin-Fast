package com.miner.out.jielin_fast.dao;

import com.miner.out.jielin_fast.entity.SysAuthorityEntity;
import com.miner.out.jielin_fast.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-25 15:08:59
 */
@Mapper
@Repository
public interface SysAuthorityDao extends BaseDao<SysAuthorityEntity> {
    List<SysAuthorityEntity> findAuthsByRoleId(Integer roleId);
}
