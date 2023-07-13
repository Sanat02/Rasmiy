package com.example.demo.model;

import com.example.demo.enums.ContactType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)


public class Contacts {
    private ContactType type;
    private String value;

}
