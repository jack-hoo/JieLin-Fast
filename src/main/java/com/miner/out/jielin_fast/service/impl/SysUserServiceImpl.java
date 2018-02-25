package com.miner.out.jielin_fast.service.impl;

import com.miner.out.jielin_fast.common.constant.AccountStatus;
import com.miner.out.jielin_fast.common.constant.ExceptionEnum;
import com.miner.out.jielin_fast.common.exception.BizException;
import com.miner.out.jielin_fast.common.utils.JwtTokenUtil;
import com.miner.out.jielin_fast.common.utils.UserDetailsUtil;
import com.miner.out.jielin_fast.dto.SysUserDTO;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.miner.out.jielin_fast.dao.SysUserDao;
import com.miner.out.jielin_fast.entity.SysUserEntity;
import com.miner.out.jielin_fast.service.SysUserService;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        String token = null;
        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetailsUtil userDetails = (UserDetailsUtil) userDetailsService.loadUserByUsername(username);
            token = jwtTokenUtil.generateToken(userDetails.getAccount());
            logger.info("##用户##[{}]登录成功", username);
        } catch (AuthenticationException e) {
            logger.info("##用户##[{}]登录失败,原因：{}", username, e.getMessage());
            //e.printStackTrace();
            throw new BizException(e.getMessage(), ExceptionEnum.LOGIN_ERR.getCode());
        }
        return token;
    }

    @Override
    public SysUserEntity findUserByAccount(String account) {
        return sysUserDao.queryByAccount(account);
    }

    @Override
    public SysUserEntity registerUser(SysUserDTO userDTO, int role, AccountStatus status) {
        SysUserEntity regUser = new SysUserEntity();
        //注册用户时候必填的资料，可拓展
        BeanUtils.copyProperties(userDTO, regUser);
        //用户角色
        regUser.setRoleId(role);
        //用户刚注册时候账号的属性
        regUser.setStatus(status.getCode());
        save(regUser);
        //返回用户的信息,清空敏感信息
        regUser.setPassword("登录密码为刚才输入的密码,由6-16位数字和字母组成");

        return regUser;
    }

    @Override
    public SysUserEntity queryObject(Integer id) {
        return sysUserDao.queryObject(id);
    }

    @Override
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserDao.queryTotal(map);
    }

    @Override
    public void save(SysUserEntity sysUser) {
        sysUser = encodeUser(sysUser);
        sysUser.setCreateTime(new Date());
        logger.info("系统用户注册成功:{}", sysUser.getUsername());
        sysUserDao.save(sysUser);
    }

    @Override
    public void update(SysUserEntity sysUser) {
        sysUser = encodeUser(sysUser);
        sysUserDao.update(sysUser);
        logger.info("密码重置成功:{}", sysUser.getUsername());
    }

    @Override
    public void delete(Integer id) {
        sysUserDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        sysUserDao.deleteBatch(ids);
    }

    /**
     * 对保存的用户密码进行加密
     *
     * @param sysUser
     * @return
     */
    private SysUserEntity encodeUser(SysUserEntity sysUser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPwd = sysUser.getPassword();
        sysUser.setPassword(encoder.encode(rawPwd));
        return sysUser;
    }
}
