package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubDocumentDto {
    private int id;
    private String typeNameKg;
    private String typeNameRu;
    private int documentId;
    private String documentNameKg;
    private String documentNameRu;
    private String subNameKg;
    private String subNameRu;
}
