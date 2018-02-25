package com.miner.out.jielin_fast.service;

import com.miner.out.jielin_fast.entity.SysAuthorityEntity;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-25 15:08:59
 */
public interface SysAuthorityService {
    List<SysAuthorityEntity> getAuthesByRoleId(Integer roleId);

    SysAuthorityEntity queryObject(Integer id);

    List<SysAuthorityEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysAuthorityEntity sysAuthority);

    void update(SysAuthorityEntity sysAuthority);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);
}
