package com.example.demo.repository;

import com.example.demo.model.Chygysh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChygyshRepository extends JpaRepository<Chygysh,Integer> {
    Optional<Chygysh> findChygyshByEnding(String ending);
}
