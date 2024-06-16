package com.example.demo.repository;

import com.example.demo.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement,Integer> {
    List<Statement> getStatementsByUserId(int userId);
}
