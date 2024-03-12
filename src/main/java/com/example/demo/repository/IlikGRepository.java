package com.example.demo.repository;

import com.example.demo.model.Ilik;
import com.example.demo.model.IlikG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IlikGRepository extends JpaRepository<IlikG,Integer> {
    Optional<IlikG> findIlikGByEnding(String ending);
}
