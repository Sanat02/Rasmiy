package com.example.demo.dao;

import com.example.demo.enums.AccountType;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;


    public List<User> getAllUsers() {
        String sql = "SELECT id, account_name as accountName, email, account_type as accountType, " +
                "password, phone_number as phoneNumber, profile_photo as profilePhoto FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }


    public Optional<User> getUserByName(String accountName) {
        String sql = "SELECT id, account_name as accountName, email, account_type as accountType, " +
                "password, phone_number as phoneNumber, profile_photo as profilePhoto FROM users WHERE account_name = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), accountName);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    public Optional<User> getUserByEmail(String email) {
        String sql = "SELECT id, account_name as accountName, email, account_type as accountType, " +
                "password, phone_number as phoneNumber, profile_photo as profilePhoto FROM users WHERE email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    public boolean isExists(String email) {
        String sql = "SELECT CASE WHEN EXISTS(SELECT * FROM users WHERE email = ?) THEN TRUE ELSE FALSE END";
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }


    public User getUserById(int id) {
        String sql = "SELECT id, account_name as accountName, email, account_type as accountType, " +
                "password, phone_number as phoneNumber, profile_photo as profilePhoto FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }


    public void updateAccountName(int userId, String newAccountName) {
        String sql = "UPDATE users SET account_name = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql, newAccountName, userId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update account name for user with ID: " + userId, e);
        }
    }


    public User getUserByPhoneNumber(String phoneNumber) {
        String sql = "SELECT id, account_name as accountName, email, account_type as accountType, " +
                "password, phone_number as phoneNumber, profile_photo as profilePhoto FROM users WHERE phone_number = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), phoneNumber);
    }


    public void createUser(User user) {
        String sql = "INSERT INTO users (id, account_name, email, account_type, password, phone_number, profile_photo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getAccountName(), user.getEmail(), String.valueOf(user.getAccountType()),
                user.getPassword(), user.getPhoneNumber(), user.getProfilePhoto());
    }


    public List<User> getAllJobSeekers() {
        String sql = "SELECT id, account_name as accountName, email, account_type as accountType, " +
                "password, phone_number as phoneNumber, profile_photo as profilePhoto FROM users WHERE account_type = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), String.valueOf(AccountType.JOB_SEEKER));
    }
}
