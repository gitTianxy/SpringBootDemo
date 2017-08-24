package com.example.service;

import com.example.dao.UserDao;
import com.example.domain.User;
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
    private RedisTemplate redisTemplate;

    private final String REDIS_USER_KEY_PREFIX = "springboot_user_";
    private final Long REDIS_USER_KEY_TIMEOUT = 60l;
    private final TimeUnit REDIS_USER_KEY_TIMEUNIT = TimeUnit.SECONDS;

    /**
     * 新增一个用户
     *
     * @param name
     * @param age
     */
    public void create(String name, Integer age) {
        User u = userDao.create(name, age);
        ValueOperations<String, User> valOpts = redisTemplate.opsForValue();
        valOpts.set(REDIS_USER_KEY_PREFIX + u.getId(), u, REDIS_USER_KEY_TIMEOUT, REDIS_USER_KEY_TIMEUNIT);
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
            if (redisTemplate.hasKey(REDIS_USER_KEY_PREFIX + u.getId())) {
                redisTemplate.delete(REDIS_USER_KEY_PREFIX + u.getId());
            }
            delCount++;
        }
        return delCount;
    }

    /**
     * 获取用户总量
     */
    public int getUserNum() {
        Set<String> keys = redisTemplate.keys(REDIS_USER_KEY_PREFIX + "*");
        if (!keys.isEmpty()) {
            return keys.size();
        } else {
            return userDao.getUserNum();
        }
    }

    /**
     * get user list
     *
     * @return
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Set<String> keys = redisTemplate.keys(REDIS_USER_KEY_PREFIX + "*");
        if (!keys.isEmpty()) {
            ValueOperations<String, User> valOpts = redisTemplate.opsForValue();
            for (String k : keys) {
                users.add(valOpts.get(k));
            }
        } else {
            users.addAll(userDao.getAllUsers());
        }
        return users;
    }

    public List<User> getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public void deleteAll() {
        userDao.deleteAllUsers();
        Set<String> keys = redisTemplate.keys(REDIS_USER_KEY_PREFIX + "*");
        if (!keys.isEmpty()) {
            for (String k : keys) {
                redisTemplate.delete(k);
            }
        }
    }

    public User getById(Long id) {
        if (redisTemplate.hasKey(REDIS_USER_KEY_PREFIX + id)) {
            ValueOperations<String, User> valOpts = redisTemplate.opsForValue();
            return valOpts.get(REDIS_USER_KEY_PREFIX + id);
        }
        return userDao.get(id);
    }

    public void update(User u) {
        userDao.update(u);
        ValueOperations<String, User> valOpts = redisTemplate.opsForValue();
        valOpts.set(REDIS_USER_KEY_PREFIX + u.getId(), u, REDIS_USER_KEY_TIMEOUT, REDIS_USER_KEY_TIMEUNIT);
    }
}
