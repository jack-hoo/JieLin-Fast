package com.miner.out.jielin_fast.service;

import com.miner.out.jielin_fast.dto.RoleAuthorityDTO;
import com.miner.out.jielin_fast.entity.SysAuthorityEntity;

import java.util.List;
import java.util.Map;

/**
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-25 15:08:59
 */
public interface SysAuthorityService {
    List<RoleAuthorityDTO> getAuthesByRoleId(Integer roleId);

    SysAuthorityEntity queryObject(Integer id);

    List<SysAuthorityEntity> queryList(Map<String, Object> map);

    Integer queryTotal(Map<String, Object> map);

    void save(SysAuthorityEntity sysAuthority);

    void update(SysAuthorityEntity sysAuthority);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);
}
