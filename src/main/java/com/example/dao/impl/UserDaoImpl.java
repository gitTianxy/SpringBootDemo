package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
    }

    @Override
    public int deleteByName(String name) {
        return jdbcTemplate.update("delete from USER where NAME = ?", name);
    }

    @Override
    public int getUserNum() {
        return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public List<User> getUserByName(String name) {
        String sql = " select * from user where name=? ";
        Object[] args = {name};
        return jdbcTemplate.query(sql, args, new UserMapper());
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from USER");
    }

    class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User u = new User();
            u.setId(rs.getLong("id"));
            u.setName(rs.getString("name"));
            u.setAge(rs.getLong("age"));
            return u;
        }
    }
}
