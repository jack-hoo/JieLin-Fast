package com.miner.out.jielin_fast.dao;

import com.miner.out.jielin_fast.entity.SysUserEntity;
import com.miner.out.jielin_fast.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * 
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-24 22:25:10
 */
@Mapper
@Repository
public interface SysUserDao extends BaseDao<SysUserEntity> {
    SysUserEntity queryByAccount(String account);
}
