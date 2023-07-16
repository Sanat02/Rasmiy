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


    //поиск резюме по категории
    public List<JobResume> getResumeByCategory(String category) {
        String sql = "select * from job_resumes where category = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), category);
    }

    //поиск резюме где зарплата <
    public List<JobResume> getResumeByCategory(Integer salary) {
        String sql = "select * from job_resumes where salary < ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), salary);
    }

    //поиск резюме где опыт >
    public List<JobResume> getResumeByCategory(int year) {
        String sql = "select * from job_resumes where experience > ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), year);
    }

    //поиск резюме по id
    public List<JobResume> getJobResumeById(int id) {
        String sql = "select * from job_resumes where id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), id);
    }

    public List<JobResume> getAllJobResumes() {
        String sql = "select * from job_resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class));
    }
    public void createJobResume(JobResume jobResume){
        String sql="INSERT INTO job_resumes(id,user_id,job_title,salary,job_description,experience,category)" +
                "VALUES(?,?,?,?,?,?,?)";
        int row=jdbcTemplate.update(sql,jobResume.getId(),jobResume.getUser_id(),jobResume.getJob_title()
        ,jobResume.getSalary(),jobResume.getJob_description(),jobResume.getExperience(),jobResume.getCategory());
    }
    public void updateJobResume(JobResume jobResume){
        String sql = "UPDATE job_resumes SET job_title = ?, salary = ?," +
                " job_description = ? , experience = ? ,category = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, jobResume.getJob_title(), jobResume.getSalary(),
                jobResume.getJob_description(),jobResume.getExperience(),jobResume.getCategory(),jobResume.getId());

        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to update resume with ID: " + jobResume.getId());
        }
    }

    public void deleteJobResume(int resumeId){
        String sql = "DELETE FROM job_resumes WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, resumeId);

        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete resume with ID: " + resumeId);
        }
    }
}
