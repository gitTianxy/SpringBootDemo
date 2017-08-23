package com.example.dao;

import com.example.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create(String name, Integer age);
    /**
     * 根据name删除一个用户高
     * @param name
     */
    int deleteByName(String name);
    /**
     * 获取用户总量
     */
    int getUserNum();

    /**
     * get user list
     * @return
     */
    List<User> getAllUsers();

    List<User> getUserByName(String name);

    /**
     * 删除所有用户
     */
    void deleteAllUsers();
}
