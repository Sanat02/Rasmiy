package com.example.demo.service;

import com.example.demo.dto.SubDocumentDto;
import com.example.demo.model.SubDocument;
import com.example.demo.repository.SubDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubDocumentService {
    private final SubDocumentRepository subDocumentRepository;

    public List<SubDocumentDto> getSubDocsByDocumentId(int documentId) {
        List<SubDocument> subDocuments = subDocumentRepository.findSubDocumentsByDocument_Id(documentId);
        return subDocuments.stream().map(e -> mapToSubDocumentDto(e)).collect(Collectors.toList());
    }

    public SubDocumentDto mapToSubDocumentDto(SubDocument subDocument) {
        return SubDocumentDto.builder()
                .id(subDocument.getId())
                .documentId(subDocument.getDocument().getId())
                .subNameKg(subDocument.getSubNameKg())
                .subNameRu(subDocument.getSubNameRu())
                .documentNameKg(subDocument.getDocument().getDocument_name_kyrgyz())
                .documentNameRu(subDocument.getDocument().getDocument_name_russian())
                .typeNameKg(subDocument.getDocumentType().getTypeNameKg())
                .typeNameRu(subDocument.getDocumentType().getTypeNameRu())
                .build();
    }
}
