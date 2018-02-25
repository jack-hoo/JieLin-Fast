package com.miner.out.jielin_fast.controller;

import java.util.List;
import java.util.Map;

import com.miner.out.jielin_fast.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.miner.out.jielin_fast.entity.SysUserEntity;
import com.miner.out.jielin_fast.service.SysUserService;
import com.miner.out.jielin_fast.common.utils.PageUtils;
import com.miner.out.jielin_fast.common.utils.Query;
import com.miner.out.jielin_fast.common.utils.Result;

/**
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-24 22:25:10
 */
@RestController
@RequestMapping("sys_user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @GetMapping("")
    @PreAuthorize("hasAuthority('user_list')")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        if (params.get("page") != null && params.get("limit") != null) {
            List<SysUserEntity> sysUserList = sysUserService.queryList(query);
            int total = sysUserService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(sysUserList, total, query.getSize(), query.getPage());
            return new Result(pageUtil);
        } else {
            List<SysUserEntity> sysUserList = sysUserService.queryList(query);
            return new Result(sysUserList);
        }

    }

    /**
     * 信息
     */
    @GetMapping("/{id}")
    public Result info(@PathVariable("id") Integer id) {
        SysUserEntity sysUser = sysUserService.queryObject(id);

        return new Result("sysUser", sysUser);
    }

    /**
     * 保存
     */
    @PostMapping("")
    public Result save(@RequestBody SysUserEntity sysUser) {
        sysUserService.save(sysUser);

        return ResultUtil.success();
    }

    /**
     * 修改
     */
    @PutMapping("")
    public Result update(@RequestBody SysUserEntity sysUser) {
        sysUserService.update(sysUser);

        return ResultUtil.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("")
    public Result delete(@RequestBody Integer[] ids) {
        sysUserService.deleteBatch(ids);

        return ResultUtil.success();
    }

}
