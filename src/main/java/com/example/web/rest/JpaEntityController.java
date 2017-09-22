package com.example.web.rest;

import com.example.domain.JpaEntity;
import com.example.service.JpaEntityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kevintian on 2017/9/20.
 */
@RestController
@RequestMapping("/rest/jpa_entity")
public class JpaEntityController {
    Logger logger = LoggerFactory.getLogger(JpaEntityController.class);
    @Autowired
    JpaEntityService jpaEntityService;

    @ApiOperation(value = "实体列表", notes = "获取实体列表")
    @GetMapping
    public ResponseEntity getList() {
        return new ResponseEntity(jpaEntityService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "实体", notes = "根据ID获取实体")
    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        JpaEntity entity = jpaEntityService.findById(id);
        if (entity == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(entity, HttpStatus.OK);
    }

    @ApiOperation(value = "创建实体")
    /*@ApiImplicitParam(name = "entity", value = "实体") */
    @PostMapping
    public ResponseEntity post(@RequestBody JpaEntity entity) {
        jpaEntityService.save(entity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(value = "更新实体", notes = "更新指定ID的实体")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体ID"),
            @ApiImplicitParam(name = "newEntity", value = "更新实体"),
    })*/
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody JpaEntity newEntity) {
        JpaEntity oldEntity = jpaEntityService.findById(id);
        if (oldEntity == null) {
            logger.error("entity not found. id={}", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        newEntity.setId(id);
        newEntity.setCreateTime(oldEntity.getCreateTime());
        jpaEntityService.save(newEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "删除实体", notes = "删除指定ID的实体")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            jpaEntityService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("delete entity fail. id={}, exception:{}", id, e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
