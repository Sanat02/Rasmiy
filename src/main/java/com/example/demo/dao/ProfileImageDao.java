package com.example.demo.dao;

import com.example.demo.model.ProfileImage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProfileImageDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(ProfileImage profileImage) {
        String sql = "insert into profile_images(userid,filename) " +
                "values  ( ? , ? )";
        jdbcTemplate.update(sql, profileImage.getUserId(), profileImage.getFileName());
    }

    public ProfileImage getImageById(int imageId) {
        String sql = "select * from profile_images where id = ?";
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProfileImage.class), imageId));
    }

    public ProfileImage getImageByUserId(int userId) {
        String sql = "select * from profile_images where userId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ProfileImage.class), userId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
