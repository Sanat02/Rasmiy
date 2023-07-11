package com.example.demo.model;

import com.example.demo.enums.ContactType;
import lombok.Data;

@Data


public class Contacts {
    private ContactType type;
    private String value;

}
