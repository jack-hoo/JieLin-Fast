package com.miner.out.jielin_fast.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import com.miner.out.jielin_fast.entity.SysRoleEntity;
import com.miner.out.jielin_fast.service.SysRoleService;
import com.miner.out.jielin_fast.common.utils.PageUtils;
import com.miner.out.jielin_fast.common.utils.Query;
import com.miner.out.jielin_fast.common.utils.Result;
import com.miner.out.jielin_fast.common.utils.ResultUtil;
/**
 *
 *
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-03-05 14:15:38
 */
@RestController
@RequestMapping("sys_role")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 查询所有纪录,供后台使用
	 */
	@GetMapping("")
	@PreAuthorize("hasAuthority('sys_role_list') or hasRole('ROLE_ADMIN')")
	public Result list(@RequestParam Map<String, Object> params) {
		//查询列表数据,params中可以包含查询条件
		Query query = new Query(params);
		return getResult(params, query);
	}

	/**
	 * 查询某个资源,供后台使用
	 */
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('sys_role_info') or hasRole('ROLE_ADMIN')")
	public Result info(@PathVariable("id") Integer id) {

		SysRoleEntity sysRole = sysRoleService.queryObject(id);
		return new Result("sysRole", sysRole);
	}

	/**
	 * 新增某个系统级别资源,供后台使用
	 */
	@PostMapping("")
	@PreAuthorize("hasAuthority('sys_role_add') or hasRole('ROLE_ADMIN')")
	public Result save(@RequestBody SysRoleEntity sysRole) {
		sysRoleService.save(sysRole);

		return ResultUtil.success();
	}

	/**
	 * 修改资源,供后台使用
	 */
	@PutMapping("")
	@PreAuthorize("hasAuthority('sys_role_update') or hasRole('ROLE_ADMIN')")
	public Result update(@RequestBody SysRoleEntity sysRole) {
		sysRoleService.update(sysRole);

		return ResultUtil.success();
	}

	/**
	 * 删除某个资源,供后台使用
	 */
	@DeleteMapping("")
	@PreAuthorize("hasAuthority('sys_role_delete') or hasRole('ROLE_ADMIN')")
	public Result delete(@RequestBody Integer[] ids) {
		sysRoleService.deleteBatch(ids);

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
			List<SysRoleEntity> sysRoleList = sysRoleService.queryList(query);
			Integer total = sysRoleService.queryTotal(query);
			PageUtils pageUtil = new PageUtils(sysRoleList, total, query.getSize(), query.getPage());
			return new Result(pageUtil);
		} else {
			List<SysRoleEntity> sysRoleList = sysRoleService.queryList(query);
			return new Result("list", sysRoleList);
		}
	}
}
