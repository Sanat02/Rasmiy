package com.example.demo.dao;

import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import com.example.demo.model.WhoInterested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WhoIsInterestedDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public WhoIsInterestedDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Выборка откликнувшихся соискателей на вакансию.
    public List<User> getInterestedJobSeekers() {
        List<User> interestedJobSeekers = new ArrayList<>();
        List<User> users = getAllUsers();
        List<WhoInterested> interesteds = getAllInterestedList();
        interesteds.forEach(whoInterested -> {
            var user = users.stream().filter(e -> e.getId() == whoInterested.getApplicant_id())
                    .findFirst().orElse(null);
            whoInterested.setApplicant(user);
            interestedJobSeekers.add(user);
        });

        return interestedJobSeekers;
    }

    //Выборка вакансий на которые откликнулся пользователь.
    public List<JobResume> getInterestedJobs() {
        List<JobResume> interestedJobs = new ArrayList<>();
        List<JobResume> jobResumes = getAllJobResumes();
        List<WhoInterested> interesteds = getAllInterestedList();
        interesteds.forEach(whoInterested -> {
            var job = jobResumes.stream().filter(e -> e.getId() == whoInterested.getJob_resume_id())
                    .findFirst().orElse(null);
            whoInterested.setJob_resume(job);
            interestedJobs.add(job);
        });

        return interestedJobs;
    }

    private List<User> getAllUsers() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    private List<JobResume> getAllJobResumes() {
        String sql = "select * from jobresume";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class));
    }

    //Выборка всех отликнувшихся пользователей
    public List<WhoInterested> getAllInterestedList() {
        String sql = "select * from whointerested";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WhoInterested.class));
    }
}
