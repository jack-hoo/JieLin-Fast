/**
 * Copyright (C), 2008-2017, 杭州迪火科技有限公司
 * FileName: XSSFilterConfig
 * Author:   shugan
 * Date:     2017/12/16 13:43
 * Description: XSS过滤配置
 */
package com.miner.out.jielin_fast.config;

import com.miner.out.jielin_fast.common.filters.XssFilter;
import com.miner.out.jielin_fast.service.SysUserService;
import com.miner.out.jielin_fast.service.impl.SysUserServiceImpl;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * 〈XSS过滤配置〉
 *
 * @author shugan
 * @create 2017/12/16
 * @since 1.0.0
 */
@Configuration
public class XSSFilterConfig {
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

    @Bean
    public SysUserService sysUserService() {
        return new SysUserServiceImpl();
    }
}