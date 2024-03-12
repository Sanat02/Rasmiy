package com.example.demo.repository;



import com.example.demo.model.Jatysh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JatyshRepository extends JpaRepository<Jatysh,Integer> {
    Optional<Jatysh> findJatyshByEnding(String ending);
}
