package com.miner.out.jielin_fast.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import com.miner.out.jielin_fast.entity.SysAuthorityEntity;
import com.miner.out.jielin_fast.service.SysAuthorityService;
import com.miner.out.jielin_fast.common.utils.PageUtils;
import com.miner.out.jielin_fast.common.utils.Query;
import com.miner.out.jielin_fast.common.utils.Result;
import com.miner.out.jielin_fast.common.utils.ResultUtil;
/**
 * 系统资源抽象层，对每个资源的访问权限写入数据库，
 * 当开发人员开发完一个接口，需要将该接口的信息(权限名称，url,资源类型）录入数据库
 * 只允许开发人员操作
 *
 *
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-03-05 14:15:38
 */
@RestController
@RequestMapping("sys_authority")
public class SysAuthorityController {
    @Autowired
    private SysAuthorityService sysAuthorityService;

    /**
     * 查询所有纪录,供后台使用
     */
    @GetMapping("")
    @PreAuthorize("hasAuthority('sys_authority_list') or hasRole('ROLE_ADMIN')")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据,params中可以包含查询条件
        Query query = new Query(params);
        return getResult(params, query);
    }

    /**
     * 查询某个资源,供后台使用
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys_authority_info') or hasRole('ROLE_ADMIN')")
    public Result info(@PathVariable("id") Integer id) {

        SysAuthorityEntity sysAuthority = sysAuthorityService.queryObject(id);
        return new Result("sysAuthority", sysAuthority);
    }

    /**
     * 新增某个系统级别资源,供后台使用
     */
    @PostMapping("")
    @PreAuthorize("hasAuthority('sys_authority_add') or hasRole('ROLE_ADMIN')")
    public Result save(@RequestBody SysAuthorityEntity sysAuthority) {
        sysAuthorityService.save(sysAuthority);

        return ResultUtil.success();
    }

    /**
     * 修改资源,供后台使用
     */
    @PutMapping("")
    @PreAuthorize("hasAuthority('sys_authority_update') or hasRole('ROLE_ADMIN')")
    public Result update(@RequestBody SysAuthorityEntity sysAuthority) {
        sysAuthorityService.update(sysAuthority);

        return ResultUtil.success();
    }

    /**
     * 删除某个资源,供后台使用
     */
    @DeleteMapping("")
    @PreAuthorize("hasAuthority('sys_authority_delete') or hasRole('ROLE_ADMIN')")
    public Result delete(@RequestBody Integer[] ids) {
        sysAuthorityService.deleteBatch(ids);

        return ResultUtil.success();
    }

    /**
     * 是否获取分页,没有page和limit参数则直接获取所有列表
     * 使用方法   /资源名称?page=1&limit=10&param1=?&sidx=[asc||desc]&order=xx
     * param1指查询参数,sidx指升序或降序,order排序字段
     * @param params
     * @param query
     * @return
     */
    private Result getResult(@RequestParam Map<String, Object> params, Query query) {
        if (params.get("page") != null && params.get("limit") != null) {
            List<SysAuthorityEntity> sysAuthorityList = sysAuthorityService.queryList(query);
            Integer total = sysAuthorityService.queryTotal(query);
            PageUtils pageUtil = new PageUtils(sysAuthorityList, total, query.getSize(), query.getPage());
            return new Result(pageUtil);
        } else {
            List<SysAuthorityEntity> sysAuthorityList = sysAuthorityService.queryList(query);
            return new Result("list", sysAuthorityList);
        }
    }
}
