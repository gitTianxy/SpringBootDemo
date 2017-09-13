package com.example.dao;

import com.example.domain.JpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by kevintian on 2017/9/13.
 */
public interface JpaEntityRepository extends JpaRepository<JpaEntity, Long> {
    @Query(value = "select e from JpaEntity e where e.fieldA=?1 and e.fieldB=?2")
    JpaEntity findByAB(String fieldA, String fieldB);
}
