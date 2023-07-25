package com.example.demo.dao;

import com.example.demo.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;

@Component

public class CategoryDao extends BaseDao {


    CategoryDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @SneakyThrows
    public Optional<Category> getCategoryById(int categoryId) {

        String sql = "SELECT * from categories where id = ? ";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class), categoryId)
        ));

    }


    @Override
    public int save(Object obj) {
        Category category = (Category) obj;
        String sql = "INSERT INTO categories(name,description) " +
                "VALUES(?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        jdbcTemplate.update(sql,id);

    }

    @Override
    public void update(Object obj) {
        Category category = (Category) obj;
        String sql = "UPDATE categories SET name = ? , description = ? WHERE id = ?";
        jdbcTemplate.update(sql, category.getName(), category.getDescription(), category.getId());
    }
}
