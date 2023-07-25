package com.example.demo.service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryDao categoryDao;

    public Optional<Category> getCategoryById(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    public CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .description(category.getDescription())
                .id(category.getId())
                .build();
    }

    public int save(CategoryDto category) {
        int categoryId=categoryDao.save(Category.builder()
                .name(category.getName())
                .description(category.getDescription()).build());
        log.info("Category saved with id:"+categoryId);
        return categoryId;
    }

    public void update(CategoryDto category) {
        categoryDao.update(Category.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build()
        );
        log.info("Category updated with id:"+category.getId());
    }
}
