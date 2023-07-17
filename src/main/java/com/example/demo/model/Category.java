package com.example.demo.model;

import lombok.*;

@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class Category {
    private int id;
    private String name;
    private String description;
}
