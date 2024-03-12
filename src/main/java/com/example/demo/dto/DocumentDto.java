package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentDto {
    private int id;
    private String document_name_kyrgyz;
    private String document_name_russian;
    private String code;
    private int SubDocumentCount;
}
