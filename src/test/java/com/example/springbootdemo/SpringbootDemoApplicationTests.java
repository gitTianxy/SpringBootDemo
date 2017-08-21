package com.example.springbootdemo;

import com.example.bean.Greeting;
import com.example.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootDemoApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testHelloController() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/hello", Map.class);
        System.out.println("-----------------" + entity.getHeaders());
        System.out.println("-----------------" + entity.getStatusCode());
        System.out.println("-----------------" + entity.getBody());
        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Autowired
    private Person person;

    @Test
    public void testPerson() {
        System.out.println(String.format("name=%s, age=%d", person.getName(), person.getAge()));
    }

}
