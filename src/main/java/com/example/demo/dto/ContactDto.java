package com.example.demo.dto;



import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ContactDto {
    @NotNull
    private String type;

    private String value;
}
