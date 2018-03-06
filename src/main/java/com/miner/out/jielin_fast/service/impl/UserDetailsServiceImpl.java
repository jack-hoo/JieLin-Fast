/**
 * Copyright (C), 2008-2017, 杭州迪火科技有限公司
 * FileName: UserDetailsServiceImpl
 * Author:   shugan
 * Date:     2017/12/17 13:08
 * Description: spring-security用户细节实现类
 */
package com.miner.out.jielin_fast.service.impl;

import com.miner.out.jielin_fast.common.exception.BizException;
import com.miner.out.jielin_fast.common.utils.UserDetailsUtil;
import com.miner.out.jielin_fast.dao.SysAuthorityDao;
import com.miner.out.jielin_fast.dao.SysRoleDao;
import com.miner.out.jielin_fast.dao.SysUserDao;
import com.miner.out.jielin_fast.dto.RoleAuthorityDTO;
import com.miner.out.jielin_fast.entity.SysAuthorityEntity;
import com.miner.out.jielin_fast.entity.SysRoleEntity;
import com.miner.out.jielin_fast.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈spring-security用户细节实现类〉
 *
 * @author shugan
 * @create 2017/12/17
 * @since 1.0.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysAuthorityDao sysAuthorityDao;
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        SysUserEntity user = sysUserDao.queryByAccount(account);
        if (user == null) {
            throw new BizException("无权访问,请登录后再试", 40003);
            //throw new UsernameNotFoundException(account);
        }
        //通过角色id找到角色信息
        SysRoleEntity sysRoleEntity = sysRoleDao.queryObject(user.getRoleId());
        //通过角色id找到对应的权限列表
        List<RoleAuthorityDTO> auths = sysAuthorityDao.findAuthsByRoleId(user.getRoleId());

        List<GrantedAuthority> authorities = new ArrayList<>();
        //添加角色
        authorities.add(sysRoleEntity);
        //添加权限
        authorities.addAll(auths);
        UserDetailsUtil userDetails = new UserDetailsUtil();
        userDetails.setAccount(user.getAccount())
                .setPwd(user.getPassword())
                .setAuths(authorities)
                .setStatus(user.getStatus());


        return userDetails;
    }
}