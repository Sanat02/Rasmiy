package com.example.demo.dao;

import com.example.demo.model.Education;
import com.example.demo.model.JobExperience;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;

@Component


public class JobExperienceDao extends BaseDao {


    JobExperienceDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

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


    @Override
    public int save(Object obj) {
        JobExperience jobExperience = (JobExperience) obj;
        String sql = "INSERT INTO job_experience(resume_id,position,start_date,end_date) " +
                "VALUES(? , ? , ? , ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, jobExperience.getResumeId());
            ps.setString(2, jobExperience.getPosition());
            ps.setDate(3, java.sql.Date.valueOf(jobExperience.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(jobExperience.getEndDate()));
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Object obj) {
        JobExperience jobExperience = (JobExperience) obj;
        String sql = "UPDATE job_experience SET resume_id = ?, position = ?, start_date = ? ," +
                " end_date = ?  WHERE id = ?";
        jdbcTemplate.update(sql, jobExperience.getResumeId(), jobExperience.getPosition(),
                jobExperience.getStartDate(), jobExperience.getEndDate(), jobExperience.getId());
    }
}
