package com.jwtTest.jwt.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    private String name;
    private String password;
    private boolean enabled;
    private List<UserAuthorities> jpaTestUserAuthorites = new ArrayList<UserAuthorities>(0);

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "enabled")
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<UserAuthorities> getjpaTestUserAuthorites() {
        return jpaTestUserAuthorites;
    }

    public void setjpaTestUserAuthorites(List<UserAuthorities> authoriteses) {
        this.jpaTestUserAuthorites = authoriteses;
    }
}
