package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {


    private final CategoryRepository categoryRepository;

    public Optional<Category> getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .description(category.getDescription())
                .id(category.getId())
                .build();
    }


    public int save(CategoryDto category) {
        Category savedCategory = categoryRepository.save(Category.builder()
                .name(category.getName())
                .description(category.getDescription()).build());
        log.info("Category saved with id:" + savedCategory.getId());
        return savedCategory.getId();
    }

    public void update(CategoryDto category) {
        Category existingCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));


        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());


        categoryRepository.save(existingCategory);
        log.info("Category updated with id: " + category.getId());
    }
}
