package com.example.application;

import com.example.mongo.dao.UserMDao;
import com.example.mongo.document.MUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMDaoTest {
    @Autowired
    private UserMDao userMDao;

    @Before
    public void before() {
        userMDao.dropCollection();
    }

    @Test
    public void testCRUD() {
        long age = 10;
        // create
        userMDao.save(new MUser("Zhang", age++, "tf"));
        userMDao.save(new MUser("Wang", age++, "tf"));
        userMDao.save(new MUser("kevin", age++, "tf"));
        userMDao.save(new MUser("forDel", age++, "tf"));
        // retrieve
        List<MUser> users = userMDao.getAll();
        for (MUser u : users) {
            System.out.println(u);
        }
        // update
        MUser zhang = userMDao.getByName("Zhang");
        zhang.setName("ZhangNEW");
        userMDao.save(zhang);
        // delete
        MUser forDel = userMDao.getByName("forDel");
        userMDao.delete(forDel);
    }
}
