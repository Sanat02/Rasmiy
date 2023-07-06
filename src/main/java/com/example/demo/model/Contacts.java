package com.example.demo.model;

import com.example.demo.enums.ContactType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class Contacts {
    private ContactType type;
    private String value;
}
