package com.example.service;

import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.rao.UserRao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * use mysql and redis as data-source
 */
@Component
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRao userRao;

    /**
     * 新增一个用户
     *
     * @param name
     * @param age
     */
    public void create(String name, Integer age) {
        User u = userDao.create(name, age);
        userRao.add(u);
    }

    /**
     * 根据name删除一个用户
     *
     * @param name
     */
    public int deleteByName(String name) {
        int delCount = 0;
        List<User> users = userDao.getUserByName(name);
        for (User u : users) {
            userDao.delete(u.getId());
            if (userRao.exists(u.getId())) {
                userRao.delete(u.getId());
            }
            delCount++;
        }
        return delCount;
    }

    /**
     * 获取用户总量
     */
    public int getUserNum() {
        int num = userRao.count();
        if (num == 0) {
            num = userDao.getUserNum();
        }
        return num;
    }

    /**
     * get user list
     *
     * @return
     */
    public List<User> getAllUsers() {
        List<User> users = userRao.getAll();
        if (users.isEmpty()) {
            users.addAll(userDao.getAllUsers());
        }
        return users;
    }

    public List<User> getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public void deleteAll() {
        userDao.deleteAllUsers();
        userRao.deleteAll();
    }

    public User getById(Long id) {
        User u = userRao.get(id);
        if (u == null) {
            u = userDao.get(id);
            if (u != null) {
                userRao.add(u);
            }
        }
        return u;
    }

    public void update(User u) {
        userDao.update(u);
        userRao.update(u);
    }
}
