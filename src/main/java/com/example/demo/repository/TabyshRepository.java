package com.example.demo.repository;



import com.example.demo.model.Tabysh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TabyshRepository extends JpaRepository<Tabysh,Integer> {
    Optional<Tabysh> findTabyshByEnding(String ending);
}
