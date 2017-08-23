package com.example.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "user")
public class MUser implements Serializable {
    @Id
    String id;
    String name;
    Long age;
    @Transient
    String tf;

    public MUser() {
    }

    public MUser(String name, Long age, String tf) {
        this.name = name;
        this.age = age;
        this.tf = tf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    @Override
    public String toString() {
        return "MUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", tf='" + tf + '\'' +
                '}';
    }
}
