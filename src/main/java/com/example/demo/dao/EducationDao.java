package com.example.demo.dao;

import com.example.demo.model.Education;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;

@Component

public class EducationDao extends BaseDao {


    EducationDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public Optional<Education> getEducationByResumeId(int id) {

        String sql = "SELECT institution_name AS institutionName, start_date AS startDate, end_date AS endDate, " +
                "degree ,id ,resume_id FROM education WHERE resume_id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Education.class), id)
        ));
    }

    @Override
    public int save(Object obj) {
        Education education = (Education) obj;
        String sql = "INSERT INTO education(resume_id,institution_name,start_date,end_date,degree) " +
                "VALUES(? , ? , ? , ? , ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, education.getResumeId());
            ps.setString(2, education.getInstitutionName());
            ps.setDate(3, java.sql.Date.valueOf(education.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(education.getEndDate()));
            ps.setString(5, education.getDegree());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM education WHERE id = ? ";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void update(Object obj) {
        Education education = (Education) obj;
        String sql = "UPDATE education SET resume_id = ?, institution_name = ?, start_date = ? ," +
                " end_date = ? ,degree=? WHERE id = ?";
        jdbcTemplate.update(sql, education.getResumeId(), education.getInstitutionName(), education.getStartDate(),
                education.getEndDate(), education.getDegree(), education.getId());

    }
}
