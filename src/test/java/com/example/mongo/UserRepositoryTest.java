package com.example.mongo;

import com.example.mongo.document.MUser;
import com.example.mongo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void before() {
        userRepository.deleteAll();
    }

    @Test
    public void testCRUD() {
        long age = 10;
        // create
        userRepository.save(new MUser("Zhang", age++, "tf"));
        userRepository.save(new MUser("Wang", age++, "tf"));
        userRepository.save(new MUser("kevin", age++, "tf"));
        userRepository.save(new MUser("forDel", age++, "tf"));
        // retrieve
        List<MUser> users = userRepository.findAll();
        for (MUser u : users) {
            System.out.println(u);
        }
        // update
        MUser zhang = userRepository.getByName("Zhang");
        zhang.setName("ZhangNEW");
        userRepository.save(zhang);
        // delete
        MUser forDel = userRepository.getByName("forDel");
        userRepository.delete(forDel);
    }
}
