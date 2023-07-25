package com.example.demo.dao;

import com.example.demo.model.JobResume;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component

public class JobResumeDao extends BaseDao {


    public JobResumeDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<JobResume> getResumeBySalary(Integer salary) {
        String sql = "SELECT id, user_id AS userId, job_title AS jobTitle, salary, job_description AS jobDescription, " +
                "experience, category_id AS categoryId FROM job_resumes WHERE salary < ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), salary);
    }


    public List<JobResume> getResumeByExperience(int experience) {
        String sql = "SELECT id, user_id AS userId, job_title AS jobTitle, salary, job_description AS jobDescription, " +
                "experience, category_id AS categoryId FROM job_resumes WHERE experience > ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), experience);
    }


    public Optional<JobResume> getJobResumeById(int id) {
        String sql = "SELECT id, user_id AS userId, job_title AS jobTitle, salary, job_description AS jobDescription, " +
                "experience, category_id AS categoryId FROM job_resumes WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), id)
        ));
    }


    public List<JobResume> getAllJobResumes() {
        String sql = "SELECT id, user_id AS userId, job_title AS jobTitle, salary, job_description AS jobDescription, " +
                "experience, category_id AS categoryId FROM job_resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class));
    }



    @Override
    public int save(Object obj) {
        JobResume jobResume = (JobResume) obj;
        String sql = "INSERT INTO job_resumes(user_id,job_title,salary,job_description,experience,category_id)" +
                "VALUES(?,?,?,?,?,?)";

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, jobResume.getUserId());
            ps.setString(2, jobResume.getJobTitle());
            ps.setInt(3, jobResume.getSalary());
            ps.setString(4, jobResume.getJobDescription());
            ps.setInt(5, jobResume.getExperience());
            ps.setInt(6, jobResume.getCategoryId());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM job_resumes WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete resume with ID: " + id);
        }

    }

    @Override
    public void update(Object obj) {
        JobResume jobResume = (JobResume) obj;
        String sql = "UPDATE job_resumes SET job_title = ?, salary = ?," +
                " job_description = ? , experience = ? ,category_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, jobResume.getJobTitle(), jobResume.getSalary(),
                jobResume.getJobDescription(), jobResume.getExperience(), jobResume.getCategoryId(), jobResume.getId());
    }
}
