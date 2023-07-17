package com.example.demo.service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dto.CategoryDto;
import com.example.demo.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryDto getCategoryById(int categoryId) {
        Category category= categoryDao.getCategoryById(categoryId);
        if (category == null) {
            return null;
        }
        CategoryDto categoryDto=CategoryDto.builder()
                .description(category.getDescription())
                .name(category.getName())
                .build();
        return categoryDto;
    }

}
