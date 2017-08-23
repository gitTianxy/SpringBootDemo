package com.example.mongo.dao;

import com.example.mongo.document.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * deprecated, MongoRepository is recommended
 */
@Component
public class UserMDao {
    @Autowired
    MongoTemplate template;

    /**
     * create
     */
    public void save(MUser u) {
        template.save(u);
    }

    /**
     * retrieve
     */
    public MUser getByName(String name) {
        Query q = new Query(Criteria.where("name").is(name));
        return template.findOne(q, MUser.class);
    }

    /**
     * get all
     */
    public List<MUser> getAll() {
        return template.findAll(MUser.class);
    }

    /**
     * update
     */
    public void update(MUser u) {
        if (u.getId() != null) {
            MUser old = template.findOne(new Query(Criteria.where("id").is(u.getId())), MUser.class);
            old.setName(u.getName());
            old.setAge(u.getAge());
            template.save(old);
        } else {
            template.save(u);
        }
    }

    /**
     * delete
     */
    public void delete(MUser u) {
        Query rmQ = new Query(Criteria.where("id").is(u.getId()));
        template.remove(rmQ, MUser.class);
    }

    /**
     * drop collection
     */
    public void dropCollection() {
        template.dropCollection(MUser.class);
    }
}
