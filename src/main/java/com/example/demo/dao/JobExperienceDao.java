package com.example.demo.dao;

import com.example.demo.model.JobExperience;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class JobExperienceDao {
    private final JdbcTemplate jdbcTemplate;

    public JobExperience getExperienceById(int id) {
        try {
            String sql = "SELECT * from job_experience" +
                    " where resume_id = ? ";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JobExperience.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
