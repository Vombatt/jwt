/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jwtTest.jwt.service;

import com.jwtTest.jwt.model.JwtUser;
import com.jwtTest.jwt.model.User;
import com.jwtTest.jwt.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author alexey
 */

@Component
public class JwtUserService {
    
    @Autowired
    UserRepository repository;
    
    @Autowired
    JwtUserFactory factory;
    
    @Autowired
    ApplicationContext context;
    
    public JwtUser getJwtUserByUsername(String name){
        
        String[] string = context.getBeanDefinitionNames();
        
        List<User> user = repository.findByName(name);
        JwtUser jwtUser = factory.createJwtUser(user.get(0));
        return jwtUser;
    } 
}
