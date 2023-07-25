package com.example.demo.dao;


import com.example.demo.model.JobResume;
import com.example.demo.model.WhoInterested;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component

public class WhoIsInterestedDao extends BaseDao {


    WhoIsInterestedDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<WhoInterested> getAllInterestedList() {
        String sql = "select * from who_interested";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WhoInterested.class));
    }

    public List<WhoInterested> getInterestedApplicants(int id) {
        String sql = "select * from who_interested where job_resume_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WhoInterested.class), id);
    }


    @Override
    public int save(Object obj) {
        WhoInterested whoInterested = (WhoInterested) obj;
        String sql = "INSERT INTO who_interested(applicant_id,job_resume_id,date) " +
                "VALUES(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, whoInterested.getApplicantId());
            ps.setInt(2, whoInterested.getJobResumeId());
            ps.setDate(3, java.sql.Date.valueOf(whoInterested.getDate()));
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM who_interested WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete resume with ID: " + id);
        }
    }

    @Override
    public void update(Object obj) {
        WhoInterested whoInterested = (WhoInterested) obj;
        String sql = "UPDATE who_interested SET applicant_id = ?, job_resume_id = ?," +
                " date= ?  WHERE id = ?";
        jdbcTemplate.update(sql, whoInterested.getApplicantId(), whoInterested.getJobResumeId(),
                whoInterested.getDate(), whoInterested.getId());

    }
}
