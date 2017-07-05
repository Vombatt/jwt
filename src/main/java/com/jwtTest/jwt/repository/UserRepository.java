/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jwtTest.jwt.repository;

import com.jwtTest.jwt.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alexey
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    public  List<User> findByName(String name);
}
