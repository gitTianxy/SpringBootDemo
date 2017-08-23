package com.example.service;

import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.dao.UserDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 新增一个用户
     *
     * @param name
     * @param age
     */
    public void create(String name, Integer age) {
        userDao.create(name, age);
    }

    /**
     * 根据name删除一个用户高
     *
     * @param name
     */
    public int deleteByName(String name) {
        return userDao.deleteByName(name);
    }

    /**
     * 获取用户总量
     */
    public int getUserNum() {
        return userDao.getUserNum();
    }

    /**
     * get user list
     *
     * @return
     */
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<User> getUserByName(String name) {
        return userDao.getUserByName(name);
    }

}
