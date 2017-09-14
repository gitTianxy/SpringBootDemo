package com.example.base.service;

import com.example.base.dao.UserDao;
import com.example.base.domain.User;
import com.example.rao.UserRao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * use mysql and redis as data-source
 */
@Service
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
    public User create(String name, Integer age) {
        User u = userDao.getByName(name);
        if (u == null) {
            u = new User(name, age);
        }
        u = userDao.save(u);
        userRao.add(u);
        return u;
    }

    /**
     * 新增一个用户
     *
     * @param name
     * @param age
     * @param passwd
     * @return
     */
    public User create(String name, Integer age, String passwd) {
        User u = userDao.getByName(name);
        if (u == null) {
            u = new User(name, age, passwd);
        }
        u = userDao.save(u);
        userRao.add(u);
        return u;
    }

    /**
     * 根据name删除一个用户
     *
     * @param name
     */
    public int deleteByName(String name) {
        int delCount = 0;

        User u = userDao.getByName(name);
        if (u != null) {
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
            num = (int) userDao.count();
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
            users.addAll(userDao.findAll());
        }
        return users;
    }

    public User getUserByName(String name) {
        return userDao.getByName(name);
    }

    public void deleteAll() {
        userDao.deleteAll();
        userRao.deleteAll();
    }

    public User getById(Long id) {
        User u = userRao.get(id);
        if (u == null) {
            u = userDao.findOne(id);
            if (u != null) {
                userRao.add(u);
            }
        }
        return u;
    }

    public void update(User u) {
        userDao.save(u);
        userRao.update(u);
    }
}
