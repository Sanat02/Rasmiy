package com.example.demo.service;

import com.example.demo.dto.EducationRecoveryStatementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatementService {
    public String
    getEducationRecoveryStatement(EducationRecoveryStatementDto educationRecoveryStatementDto){
        System.out.println("Success getting !");
        return "Success getting !";
    }
}
