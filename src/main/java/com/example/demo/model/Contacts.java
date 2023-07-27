package com.example.demo.model;

import com.example.demo.enums.ContactType;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder


public class Contacts {
    private int id;
    private int resumeId;
    private ContactType type;
    private String value;

}
