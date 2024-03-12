package com.example.demo.repository;

import com.example.demo.model.Barysh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaryshRepository extends JpaRepository<Barysh,Integer> {
    Optional<Barysh> findBaryshByEnding(String ending);
}
