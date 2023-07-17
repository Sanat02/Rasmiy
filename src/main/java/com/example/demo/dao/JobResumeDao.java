package com.example.demo.dao;

import com.example.demo.model.JobResume;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobResumeDao {

    private final JdbcTemplate jdbcTemplate;


    // Search resumes where salary is less than a given value
    public List<JobResume> getResumeBySalary(Integer salary) {
        String sql = "SELECT id, user_id AS userId, job_title AS jobTitle, salary, job_description AS jobDescription, " +
                "experience, category_id AS categoryId FROM job_resumes WHERE salary < ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), salary);
    }

    // Search resumes where experience is greater than a given value
    public List<JobResume> getResumeByExperience(int experience) {
        String sql = "SELECT id, user_id AS userId, job_title AS jobTitle, salary, job_description AS jobDescription, " +
                "experience, category_id AS categoryId FROM job_resumes WHERE experience > ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), experience);
    }

    // Search resumes by ID
    public JobResume getJobResumeById(int id) {
        String sql = "SELECT id, user_id AS userId, job_title AS jobTitle, salary, job_description AS jobDescription, " +
                "experience, category_id AS categoryId FROM job_resumes WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JobResume.class), id);
    }

    // Get all job resumes
    public List<JobResume> getAllJobResumes() {
        String sql = "SELECT id, user_id AS userId, job_title AS jobTitle, salary, job_description AS jobDescription, " +
                "experience, category_id AS categoryId FROM job_resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class));
    }

    public void createJobResume(JobResume jobResume) {
        String sql = "INSERT INTO job_resumes(id,user_id,job_title,salary,job_description,experience,category_id)" +
                "VALUES(?,?,?,?,?,?,?)";
        int row = jdbcTemplate.update(sql, jobResume.getId(), jobResume.getUserId(), jobResume.getJobTitle()
                , jobResume.getSalary(), jobResume.getJobDescription(), jobResume.getExperience(), jobResume.getCategoryId());
    }

    public void updateJobResume(JobResume jobResume) {
        String sql = "UPDATE job_resumes SET job_title = ?, salary = ?," +
                " job_description = ? , experience = ? ,category_id = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, jobResume.getJobTitle(), jobResume.getSalary(),
                jobResume.getJobDescription(), jobResume.getExperience(), jobResume.getCategoryId(), jobResume.getId());

        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to update resume with ID: " + jobResume.getId());
        }
    }

    public void deleteJobResume(int resumeId) {
        String sql = "DELETE FROM job_resumes WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, resumeId);

        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete resume with ID: " + resumeId);
        }
    }
}
