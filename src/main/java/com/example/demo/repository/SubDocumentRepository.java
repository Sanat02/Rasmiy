package com.example.demo.repository;

import com.example.demo.model.SubDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDocumentRepository extends JpaRepository<SubDocument,Integer> {
    List<SubDocument> findSubDocumentsByDocument_Id(int documentId);
}
