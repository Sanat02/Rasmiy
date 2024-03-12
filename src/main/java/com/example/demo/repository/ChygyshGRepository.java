package com.example.demo.repository;


import com.example.demo.model.ChygyshG;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChygyshGRepository extends JpaRepository<ChygyshG,Integer> {
    Optional<ChygyshG> findChygyshGByEnding(String ending);
}
