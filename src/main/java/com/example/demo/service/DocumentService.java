package com.example.demo.service;

import com.example.demo.dto.DocumentDto;
import com.example.demo.dto.JobResumeDto;
import com.example.demo.model.Document;
import com.example.demo.model.JobResume;
import com.example.demo.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {
    private final DocumentRepository repository;

    public List<DocumentDto> getAllDocuments(){
      List<Document> documents=repository.findAll();
        return documents.stream().map(e->mapToDocumentDto(e)).collect(Collectors.toList());
    }
    public DocumentDto getDocumentById(int documentid){
        Optional<Document> document=repository.findById(documentid);
        return mapToDocumentDto(document.get());
    }
    public DocumentDto mapToDocumentDto(Document document) {
        return DocumentDto.builder()
                .id(document.getId())
                .document_name_kyrgyz(document.getDocument_name_kyrgyz())
                .document_name_russian(document.getDocument_name_russian())
                .code(document.getCode())
                .SubDocumentCount(document.getDocuments().size())
                .build();

    }
}
