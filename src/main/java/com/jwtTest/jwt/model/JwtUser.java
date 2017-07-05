/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jwtTest.jwt.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author alexey
 */
public class JwtUser {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    
    public JwtUser(){
        
    }
    
    public JwtUser(String username, String password, Collection<? extends GrantedAuthority> authorities){
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }
    
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
    }
}
