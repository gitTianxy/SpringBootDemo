package com.example.base.domain;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    Long id;
    @Column(unique = true)
    String name;
    Integer age;
    String passwd;
    String roles;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age, String passwd) {
        this.name = name;
        this.age = age;
        this.passwd = passwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean equals(User u) {
        return this.id == u.getId();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String[] getRoles() {
        if (roles==null) {
            return new String[0];
        } else {
            return roles.split(",");
        }
    }

    public void setRoles(String[] roles) {
        this.roles = Arrays.toString(roles);
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", passwd='" + passwd + '\'' +
                ", roles=\'" + roles +
                '}';
    }
}
