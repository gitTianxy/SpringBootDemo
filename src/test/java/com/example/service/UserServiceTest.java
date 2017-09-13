package com.example.service;

import com.example.application.Application;
import com.example.domain.User;
import com.example.service.UserService;
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
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Before
    public void before() {
        userService.deleteAll();
    }

    @Test
    public void testCRUD() throws Exception {
        int initCount = userService.getUserNum();
        // 插入5个用户
        userService.create("a", 1);
        userService.create("b", 2);
        userService.create("c", 3);
        userService.create("d", 4);
        userService.create("e", 5);
        // 查数据库，应该有5个用户
        Assert.assertEquals(initCount + 5, userService.getUserNum());
        // 删除两个用户
        int delCount = userService.deleteByName("a");
        delCount += userService.deleteByName("e");
        // update
        List<User> dlist = userService.getUserByName("c");
        for (User u : dlist) {
            u.setName(u.getName() + "_NEW");
            userService.update(u);
        }
        // list users
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
    }
}
