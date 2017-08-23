package com.example.mongo.repository;

import com.example.mongo.document.MUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * recommended
 */
@Repository
public interface UserRepository extends MongoRepository<MUser, String> {
    MUser getByName(String name);
}
