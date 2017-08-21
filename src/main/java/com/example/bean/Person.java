package com.example.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {
    @Value("${com.test.person.name}")
    String name;
    @Value("${com.test.person.age}")
    Long age;

    public String getName() {
        return name;
    }

    public Long getAge() {
        return age;
    }
}
