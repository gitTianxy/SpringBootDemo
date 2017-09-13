package com.example.service;

import com.example.dao.JpaEntityRepository;
import com.example.domain.JpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by kevintian on 2017/9/13.
 */
@Service
public class JpaEntityService {
    @Autowired
    JpaEntityRepository repository;

    /**
     * save: create or update
     */
    @Transactional
    public JpaEntity save(JpaEntity entity) {
        if (entity.getId() == null) {
            JpaEntity old = repository.findByAB(entity.getFieldA(), entity.getFieldB());
            if (old != null) {
                entity.setId(old.getId());
            }
        }
        if (entity.getCreateTime() == null) {
            entity.setCreateTime(new Date());
        }
        entity.setUpdateTime(new Date());
        return repository.save(entity);
    }

    /**
     * retrieve
     */
    public JpaEntity findById(Long id) {
        return repository.findOne(id);
    }

    public JpaEntity findByAB(String fieldA, String fieldB) {
        return repository.findByAB(fieldA, fieldB);
    }

    public List<JpaEntity> findAll() {
        return repository.findAll();
    }

    /**
     * delete
     */
    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }
}
