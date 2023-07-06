package com.example.demo.enums;

public enum AccountType {
    JOB_SEEKER("работадатель"),
    EMPLOYER("соискатель");
    private String value;

    AccountType(String value) {
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
