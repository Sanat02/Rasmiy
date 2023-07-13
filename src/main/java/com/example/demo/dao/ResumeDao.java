package com.example.demo.dao;

import com.example.demo.enums.AccountType;
import com.example.demo.enums.ContactType;
import com.example.demo.model.Contacts;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component


public class ResumeDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ResumeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   public List<Resume> getAllResumes(){
        String sql="select* from resumes";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Resume.class));
   }

}
