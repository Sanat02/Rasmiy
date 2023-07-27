package com.example.demo.dao;

import com.example.demo.model.Contacts;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactsDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Contacts> getContactsByResumeId(int id) {
        String sql = "select * from contacts where resume_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contacts.class), id);
    }

    public void save(List<Contacts> contactsList) {
        String sql = "INSERT INTO contacts (resume_id, contact_type, contact_value) " +
                "VALUES (?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<>();
        for (Contacts contacts : contactsList) {
            Object[] args = new Object[]{
                    contacts.getResumeId(),
                    contacts.getType().name(),
                    contacts.getValue()
            };
            batchArgs.add(args);
        }

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

}
