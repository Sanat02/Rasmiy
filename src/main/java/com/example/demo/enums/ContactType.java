package com.example.demo.enums;

public enum ContactType {
    TELEGRAM("телеграм"),
    EMAIL("email"),
    PHONE("номер телефона");
    private String value;

    ContactType(String value) {
        this.value = value;
    }
    public String getValue()
    {
        return value;
    }
}
