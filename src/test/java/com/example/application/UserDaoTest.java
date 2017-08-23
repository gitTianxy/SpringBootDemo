package com.example.application;

import com.example.dao.UserDao;
import com.example.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Before
    public void before() {
//        userDao.deleteAllUsers();
    }

    @Test
    public void test() throws Exception {
        int initCount = userDao.getUserNum();
        // 插入5个用户
        userDao.create("a", 1);
        userDao.create("b", 2);
        userDao.create("c", 3);
        userDao.create("d", 4);
        userDao.create("e", 5);
        // 查数据库，应该有5个用户
        Assert.assertEquals(initCount + 5, userDao.getUserNum());
        // 删除两个用户
        int delCount = userDao.deleteByName("a");
        delCount += userDao.deleteByName("e");
        // 查数据库，应该有3个用户
        Assert.assertEquals(initCount + 5 - delCount, userDao.getUserNum());
        // list users
        List<User> users = userDao.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testGetUserByName() {
        List<User> users = userDao.getUserByName("b");
        for (User u : users) {
            System.out.println(u);
        }
    }
}
