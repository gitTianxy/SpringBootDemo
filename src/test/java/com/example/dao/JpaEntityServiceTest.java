package com.example.dao;

import com.example.application.Application;
import com.example.domain.JpaEntity;
import com.example.service.JpaEntityService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kevintian on 2017/9/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JpaEntityServiceTest {
    @Resource
    JpaEntityService service;

    @Before
    public void before() {
        List<JpaEntity> list = service.findAll();
        System.out.println("***data BEFORE test***");
        for(JpaEntity entity : list) {
            System.out.println(entity);
        }

    }

    @Test
    public void testSave() {
        // save new
        service.save(new JpaEntity("field A2", "field B2"));
        // update
        service.save(new JpaEntity("field A", "field B"));
    }

    @Test
    public void testFindById() {
        System.out.println(String.format("id=%s, entity: %s", 1l, service.findById(1l)));
        System.out.println(String.format("id=%s, entity: %s", 6l, service.findById(6l)));
    }

    @Test
    public void testDelete() {
        service.delete(6l);
    }

    @After
    public void after() {
        List<JpaEntity> list = service.findAll();
        System.out.println("***data AFTER test***");
        for(JpaEntity entity : list) {
            System.out.println(entity);
        }
    }
}
