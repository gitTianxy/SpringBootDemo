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
    public User create(String name, Integer age) {
        int count = jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
        if (count < 1) {
            throw new RuntimeException(String.format("fail save User(%s, %s) to db", name, age));
        }
        return getUserByName(name).get(0);
    }

    @Override
    public int delete(Long id) {
        int count = jdbcTemplate.update("delete from USER where id = ?", id);
        if (count < 1) {
            throw new RuntimeException(String.format("delete user failed. id=%s", id));
        }
        return count;
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

    @Override
    public User get(Long id) {
        String sql = "select * from user where id=?";
        Long[] params = {id};
        return jdbcTemplate.query(sql, params, new UserMapper()).get(0);
    }

    @Override
    public void update(User u) {
        String sql = "update user set name=?, age=? where id=?";
        Object[] params = {u.getName(), u.getAge(), u.getId()};
        jdbcTemplate.update(sql, params);
    }
}
