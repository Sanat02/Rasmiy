package com.example.demo.enums;

public enum SklonenieType {
    ILIK("Илик жөндөмөсү"),
    BARYSH("Барыш жөндөмөсү"),
    TABYSH("Табыш жөндөмөсү"),
    JATYSH("Жатыш жөндөмөсү"),
    CHYGYSH("Чыгыш жөндөмөсү");
    private String value;

    SklonenieType(String value) {
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
