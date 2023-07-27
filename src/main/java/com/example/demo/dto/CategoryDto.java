package com.example.demo.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    private int id;

    @Pattern(regexp = "^[^0-9]*$", message = "category name should not contain numbers!")
    private String name;

    private String description;
}
