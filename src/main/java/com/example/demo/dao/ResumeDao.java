package com.example.demo.dao;


import com.example.demo.model.Resume;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.*;

@Component


public class ResumeDao extends BaseDao {


    ResumeDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<Resume> getAllResumes() {
        String sql = "SELECT id, user_id AS userId, job, expected_salary AS expectedSalary FROM resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Optional<Resume> getResumeById(int id) {
        String sql = "SELECT id, user_id AS userId, job, expected_salary AS expectedSalary FROM resumes WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
        ));
    }

    public List<Resume> getResumesByUserId(int userId){
        String sql="SELECT id, job, expected_salary AS expectedSalary FROM resumes WHERE user_id = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Resume.class),userId);
    }


    public List<Resume> getResumeByJob(String job) {
        String sql = "SELECT id, user_id AS userId, job, expected_salary AS expectedSalary FROM resumes WHERE job = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), job);
    }

    @Override
    public int save(Object obj) {
        Resume resume = (Resume) obj;
        String sql = "INSERT INTO resumes (user_id, expected_salary, job) VALUES (?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, resume.getUserId());
            ps.setInt(2, resume.getExpectedSalary());
            ps.setString(3, resume.getJob());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Object obj) {
        Resume resume = (Resume) obj;
        String sql = "UPDATE resumes SET user_id = ?, job = ?, expected_salary = ? WHERE id = ?";
        jdbcTemplate.update(sql, resume.getUserId(), resume.getJob(), resume.getExpectedSalary(), resume.getId());
    }
}
