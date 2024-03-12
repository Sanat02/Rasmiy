package com.example.demo.repository;


import com.example.demo.model.JatyshG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JatyshGRepository extends JpaRepository<JatyshG,Integer> {
    Optional<JatyshG> findJatyshGByEnding(String ending);
}
