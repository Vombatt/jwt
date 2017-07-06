package com.jwtTest.jwt.repository;

import com.jwtTest.jwt.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    public List<User> findByName(String name);
}
