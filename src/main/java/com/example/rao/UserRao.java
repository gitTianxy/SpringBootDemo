package com.example.rao;

import com.example.base.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class UserRao {
    @Autowired
    private RedisTemplate redisTemplate;

    private final String KEY_PREFIX = "springboot.user.";
    private final Long KEY_TIMEOUT = 60l;
    private final TimeUnit KEY_TIMEUNIT = TimeUnit.SECONDS;

    public void add(User u) {
        ValueOperations<String, User> valOpts = redisTemplate.opsForValue();
        valOpts.set(KEY_PREFIX + u.getId(), u, KEY_TIMEOUT, KEY_TIMEUNIT);
    }

    public User get(Long id) {
        ValueOperations<String, User> valOpts = redisTemplate.opsForValue();
        return valOpts.get(KEY_PREFIX + id);
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(KEY_PREFIX + "*");
        if (!keys.isEmpty()) {
            ValueOperations<String, User> valOpts = redisTemplate.opsForValue();
            for (String k : keys) {
                users.add(valOpts.get(k));
            }
        }
        return users;
    }

    public void update(User u) {
        add(u);
    }

    public void delete(Long id) {
        redisTemplate.delete(KEY_PREFIX + id);
    }

    public int count() {
        Set<String> keys = redisTemplate.keys(KEY_PREFIX + "*");
        return keys.size();
    }

    public void deleteAll() {
        Set<String> keys = redisTemplate.keys(KEY_PREFIX + "*");
        if (!keys.isEmpty()) {
            for (String k : keys) {
                redisTemplate.delete(k);
            }
        }
    }

    public boolean exists(Long id) {
        return redisTemplate.hasKey(KEY_PREFIX + id);
    }
}
