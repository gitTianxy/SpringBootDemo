package com.example.dao;

import com.example.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    User create(String name, Integer age);

    User get(Long id);

    void update(User u);

    /**
     *
     * @param id
     * @return
     */
    int delete(Long id);
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
