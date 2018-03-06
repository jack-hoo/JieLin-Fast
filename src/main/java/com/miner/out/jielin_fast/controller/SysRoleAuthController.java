package com.miner.out.jielin_fast.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.miner.out.jielin_fast.common.utils.ResultUtil;
import com.miner.out.jielin_fast.entity.SysAuthorityEntity;
import com.miner.out.jielin_fast.service.SysAuthorityService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.miner.out.jielin_fast.entity.SysRoleAuthEntity;
import com.miner.out.jielin_fast.service.SysRoleAuthService;
import com.miner.out.jielin_fast.common.utils.PageUtils;
import com.miner.out.jielin_fast.common.utils.Query;
import com.miner.out.jielin_fast.common.utils.Result;

/**
 * 角色权限管理接口，所有角色的权限分配都在这里管理
 * 这里只允许系统管理员操作
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-25 15:08:59
 */
@RestController
@RequestMapping("/role_auth")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SysRoleAuthController {
    @Autowired
    private SysRoleAuthService sysRoleAuthService;
    @Autowired
    private SysAuthorityService sysAuthorityService;
    //查询所有角色
    //查询所有权限

    /**
     * 查询角色拥有的权限
     * @param roleId
     * @return
     */
    @GetMapping("")
    public Result getAuths(@RequestParam(value = "role_id", required = true) Integer roleId) {
        return ResultUtil.success(sysAuthorityService.getAuthesByRoleId(roleId));
    }

    /**
     * 给角色添加权限
     * @param sysRoleAuth
     * @return
     */
    @PostMapping("")
    public Result addAuth(@RequestBody SysRoleAuthEntity sysRoleAuth) {
        sysRoleAuthService.save(sysRoleAuth);
        return ResultUtil.success();
    }

    /**
     * 批量为角色添加权限
     * @param auths
     * @param roleId
     * @return
     */
    @PostMapping("/batch")
    public Result addAuths(@RequestBody Integer[] auths,@RequestParam(value = "role_id",required = true) Integer roleId) {
        List<SysRoleAuthEntity> roleAuthEntities = new ArrayList<>();
        for (int i = 0; i < auths.length; i++) {
            SysRoleAuthEntity roleAuthEntity = new SysRoleAuthEntity();
            roleAuthEntity.setRoleId(roleId)
                    .setAuthId(auths[i]);
            roleAuthEntities.add(roleAuthEntity);
        }
        return ResultUtil.success(sysRoleAuthService.saveBatch(roleAuthEntities));
    }
    /**
     * 删除角色拥有的权限
     */
    @DeleteMapping("")
    public Result delete(@RequestBody Integer[] ids) {
        sysRoleAuthService.deleteBatch(ids);
        return ResultUtil.success();
    }

}
