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
            String sql = "SELECT id, resume_id as resumeId, position, start_date as startDate, end_date as endDate " +
                    "FROM job_experience " +
                    "WHERE resume_id = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JobExperience.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
