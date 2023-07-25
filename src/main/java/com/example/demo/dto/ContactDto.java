package com.example.demo.dto;

import com.example.demo.enums.ContactType;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ContactDto {
    private ContactType type;
    private String value;
}
