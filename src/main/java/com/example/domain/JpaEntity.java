package com.example.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by kevintian on 2017/9/13.
 */
@Entity
@Table(name = "jpa_entity")
@Data
public class JpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "f_a")
    String fieldA;
    @Column(name = "f_b")
    String fieldB;
    @Column(name = "create_time")
    @CreatedDate
    Date createTime;
    @Column(name = "update_time")
    @LastModifiedDate
    Date updateTime;

    public JpaEntity() {
    }

    public JpaEntity(String fieldA, String fieldB) {
        this.fieldA = fieldA;
        this.fieldB = fieldB;
    }

    @Override
    public String toString() {
        return "JpaEntity{" +
                "id=" + id +
                ", fieldA='" + fieldA + '\'' +
                ", fieldB='" + fieldB + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
