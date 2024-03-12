package com.example.demo.repository;

import com.example.demo.model.Ilik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IlikRepository extends JpaRepository<Ilik,Integer> {

    Optional<Ilik> findIlikByEnding(String ending);
}
