package com.example.demo.repository;

import com.example.demo.model.BaryshG;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaryshGRepository extends JpaRepository<BaryshG,Integer> {
    Optional<BaryshG> findBaryshGByEnding (String ending);
}
