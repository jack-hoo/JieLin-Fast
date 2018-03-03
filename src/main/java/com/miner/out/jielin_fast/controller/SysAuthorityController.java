package com.miner.out.jielin_fast.controller;

import java.util.List;
import java.util.Map;

import com.miner.out.jielin_fast.common.exception.BizException;
import com.miner.out.jielin_fast.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.miner.out.jielin_fast.entity.SysAuthorityEntity;
import com.miner.out.jielin_fast.service.SysAuthorityService;
import com.miner.out.jielin_fast.common.utils.PageUtils;
import com.miner.out.jielin_fast.common.utils.Query;
import com.miner.out.jielin_fast.common.utils.Result;

/**
 * 系统资源抽象层，对每个资源的访问权限写入数据库，
 * 当开发人员开发完一个接口，需要将该接口的信息(权限名称，url,资源类型）录入数据库
 * 只允许开发人员操作
 *
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-25 15:08:59
 */
@RestController
@RequestMapping("sys_authority")
public class SysAuthorityController {
    @Autowired
    private SysAuthorityService sysAuthorityService;

    //

    /**
     * 获取权限列表
     *
     * @param params
     * @return
     */
    @GetMapping("")
    @PreAuthorize("hasAuthority('sys_authority_list')")
    public Result list(@RequestParam Map<String, Object> params) {
        if (params.get("page") != null) {
            String page = (String) params.get("page");
            Integer integer = Integer.valueOf(page);
            if (integer <= 0) {
                throw new BizException("分页参数不正确", 40009);
            }
        }
        //查询列表数据
        Query query = new Query(params);
        if (params.get("page") != null && params.get("limit") != null) {
            List<SysAuthorityEntity> sysAuthorityList = sysAuthorityService.queryList(query);
            Integer total = sysAuthorityService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(sysAuthorityList, total, query.getSize(), query.getPage());
            return new Result("pages", pageUtil);
        } else {
            List<SysAuthorityEntity> sysAuthorityList = sysAuthorityService.queryList(query);
            return new Result("list", sysAuthorityList);
        }

    }

    /**
     * 信息
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys_authority_single')")
    public Result info(@PathVariable("id") Integer id) {
        SysAuthorityEntity sysAuthority = sysAuthorityService.queryObject(id);

        return new Result("sysAuthority", sysAuthority);
    }

    /**
     * 保存
     */
    @PostMapping("")
    @PreAuthorize("hasAuthority('sys_authority_save')")
    public Result save(@RequestBody SysAuthorityEntity sysAuthority) {
        sysAuthorityService.save(sysAuthority);

        return ResultUtil.success();
    }

    /**
     * 修改
     */
    @PutMapping("")
    @PreAuthorize("hasAuthority('sys_authority_update')")
    public Result update(@RequestBody SysAuthorityEntity sysAuthority) {
        sysAuthorityService.update(sysAuthority);

        return ResultUtil.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("")
    @PreAuthorize("hasAuthority('sys_authority_delete')")
    public Result delete(@RequestBody Integer[] ids) {
        sysAuthorityService.deleteBatch(ids);

        return ResultUtil.success();
    }

}
