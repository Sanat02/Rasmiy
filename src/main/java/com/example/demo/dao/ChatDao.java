package com.example.demo.dao;

import com.example.demo.model.Chat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;

@Component
public class ChatDao extends BaseDao {
    ChatDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public int save(Object obj) {
        Chat chat = (Chat) obj;
        String sql = "INSERT INTO chats (userId,employerId,message,messageDate)  " +
                "VALUES(?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, chat.getUserId());
            ps.setInt(2, chat.getEmployerId());
            ps.setString(3, chat.getMessage());
            ps.setDate(4, java.sql.Date.valueOf(chat.getMessageDate()));
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Object obj) {

    }
}
