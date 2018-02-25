package com.miner.out.jielin_fast.controller;

import java.util.List;
import java.util.Map;

import com.miner.out.jielin_fast.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.miner.out.jielin_fast.entity.SysRoleEntity;
import com.miner.out.jielin_fast.service.SysRoleService;
import com.miner.out.jielin_fast.common.utils.PageUtils;
import com.miner.out.jielin_fast.common.utils.Query;
import com.miner.out.jielin_fast.common.utils.Result;




/**
 * 
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2018-02-25 15:08:59
 */
@RestController
@RequestMapping("sys_role")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 列表
	 */
	@GetMapping("")
	public Result list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		if (params.get("page") != null && params.get("limit")!= null){
			List<SysRoleEntity> sysRoleList = sysRoleService.queryList(query);
			int total = sysRoleService.queryTotal(query);
			
			PageUtils pageUtil = new PageUtils(sysRoleList, total, query.getSize(), query.getPage());
            return new Result("pages", pageUtil);
		}else{
			List<SysRoleEntity> sysRoleList = sysRoleService.queryList(query);
			return new Result("list", sysRoleList);
		}
		
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/{id}")
	public Result info(@PathVariable("id") Integer id){
		SysRoleEntity sysRole = sysRoleService.queryObject(id);
		
		return new Result("sysRole", sysRole);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("")
	public Result save(@RequestBody SysRoleEntity sysRole){
		sysRoleService.save(sysRole);
		
		return ResultUtil.success();
	}
	
	/**
	 * 修改
	 */
	@PutMapping("")
	public Result update(@RequestBody SysRoleEntity sysRole){
		sysRoleService.update(sysRole);
		
		return ResultUtil.success();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping("")
	public Result delete(@RequestBody Integer[] ids){
		sysRoleService.deleteBatch(ids);
		
		return ResultUtil.success();
	}
	
}
