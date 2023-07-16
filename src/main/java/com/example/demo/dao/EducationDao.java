package com.example.demo.dao;

import com.example.demo.model.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EducationDao {
    private final JdbcTemplate jdbcTemplate;

    public Education getEducationById(int id) {
        try {
            String sql = "SELECT * FROM education where resume_id = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Education.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }


    }
}
