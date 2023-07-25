package com.example.demo.dao;

import com.example.demo.model.JobList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component

public class JobsListDao extends BaseDao {


    JobsListDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<JobList> getAllJobs() {
        String sql = "SELECT id, publisher_id as publisherId, category_id as categoryId, date " +
                "FROM vacancies";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobList.class));
    }


    public List<JobList> getJobByCategory(String category) {
        String sql = "select * from vacancies where category = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobList.class), category);
    }


    @Override
    public int save(Object obj) {
        JobList jobList = (JobList) obj;
        String sql = "INSERT INTO vacancies(publisher_id,category_id,date) " +
                " VALUES(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, jobList.getPublisherId());
            ps.setInt(2, jobList.getCategoryId());
            ps.setDate(3, java.sql.Date.valueOf(jobList.getDate()));
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM vacancies WHERE id=?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void update(Object obj) {
        JobList jobList = (JobList) obj;
        String sql = "UPDATE vacancies SET publisher_id = ? ,category_id = ? , " +
                "date = ? WHERE id = ?";
        jdbcTemplate.update(sql, jobList.getPublisherId(), jobList.getCategoryId(),
                jobList.getDate(), jobList.getId());

    }
}
