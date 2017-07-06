/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwtTest.jwt.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwtTest.jwt.service.TestService;
import com.jwtTest.jwt.service.TokenAuthenticationService;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author alexey
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    
    @Autowired
    public TestService service;
    
    @Autowired
    public TokenAuthenticationService tokenAuthenticationService;

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }
    
//     @Autowired
//    private UserDetailsService userDetailsService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        AccountCredentials creds = new ObjectMapper()
                .readValue(request.getInputStream(), AccountCredentials.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(creds.getUsername(),
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, 
            FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        
        
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(auth.getName());
        String headerRole = ""; 
        Set<String> roles = AuthorityUtils
                .authorityListToSet(auth.getAuthorities());
        StringBuffer stringBuffer = new StringBuffer();
        if (roles.contains("ROLE_ADMIN")) {
            headerRole = "admin";
        }else {
        if (roles.contains("ROLE_USER")) {
            headerRole = "user";
        }
        }
        res.addHeader("Roles", headerRole);
//        res.addHeader("TESSSTTHEEEAADERR", service.getTest());
        
        tokenAuthenticationService.addAuthentication(res, auth.getName());
    }

}
