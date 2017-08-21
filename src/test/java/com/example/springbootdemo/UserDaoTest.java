package com.example.springbootdemo;

import com.example.dao.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test() throws Exception {
        int initCount = userDao.getAllUsers().intValue();
        // 插入5个用户
        userDao.create("a", 1);
        userDao.create("b", 2);
        userDao.create("c", 3);
        userDao.create("d", 4);
        userDao.create("e", 5);
        // 查数据库，应该有5个用户
        Assert.assertEquals(initCount + 5, userDao.getAllUsers().intValue());
        // 删除两个用户
        int delCount = userDao.deleteByName("a");
        delCount += userDao.deleteByName("e");
        // 查数据库，应该有5个用户
        Assert.assertEquals(initCount + 5 - delCount, userDao.getAllUsers().intValue());
    }
}
