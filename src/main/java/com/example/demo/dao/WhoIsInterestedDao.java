package com.example.demo.dao;

import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import com.example.demo.model.WhoInterested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WhoIsInterestedDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public WhoIsInterestedDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //Выборка всех отликнувшихся пользователей
    public List<WhoInterested> getAllInterestedList() {
        String sql = "select * from who_interested";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WhoInterested.class));
    }

}
