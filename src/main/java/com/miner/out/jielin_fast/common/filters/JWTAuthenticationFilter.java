/**
 * Copyright (C), 2008-2017, 杭州迪火科技有限公司
 * FileName: JWTAuthenticationFilter
 * Author:   shugan
 * Date:     2017/12/17 13:20
 * Description: 认证过滤器
 */
package com.miner.out.jielin_fast.common.filters;

import com.miner.out.jielin_fast.common.utils.JwtTokenUtil;
import com.miner.out.jielin_fast.common.utils.UserDetailsUtil;
import com.miner.out.jielin_fast.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈认证过滤器〉
 *
 * @author shugan
 * @create 2017/12/17
 * @since 1.0.0
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.secret}")
    private String secret;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestHeader = request.getHeader(header);

        if (requestHeader == null || !requestHeader.startsWith(tokenHead)) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        /*UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("jack",null,new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);*/
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (token != null) {
            // parse the token.
            final String authToken = token.substring(tokenHead.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            final UserDetailsUtil userDetails = (UserDetailsUtil) userDetailsService.loadUserByUsername(username);

            if (userDetails != null) {
                return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
            }
            return null;
        }
        return null;
    }
}