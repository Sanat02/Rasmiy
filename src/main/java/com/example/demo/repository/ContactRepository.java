package com.example.demo.repository;

import com.example.demo.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contacts, Integer> {
    //    @Query("select c1_0.id, c1_0.resume.id, c1_0.type, c1_0.value from Contacts c1_0 where c1_0.resume.id=?")
//    List<Contacts> findByResumeId(@Param("resumeId") int resumeId);
//    @Query("SELECT c FROM Contacts c WHERE c.resume.id = :resumeId")
//    List<Contacts> findByResumeId(@Param("resumeId") int resumeId);
    List<Contacts> findContactsByResumeId(int resumeId);

}
