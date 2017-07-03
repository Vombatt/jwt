/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jwtTest.jwt.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alexey
 */
@RestController
public class RestControllers {

    @RequestMapping(value = "/user")
    public String userInfo(){
        return "user String";
    }
    
    @RequestMapping(value = "/admin")
    public String adminInfo(){
        return "admin String";
    }
}
