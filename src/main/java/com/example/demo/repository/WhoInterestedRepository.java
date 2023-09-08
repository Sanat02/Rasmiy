package com.example.demo.repository;


import com.example.demo.model.WhoInterested;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WhoInterestedRepository extends JpaRepository<WhoInterested,Integer> {
    Optional<WhoInterested> findWhoInterestedByApplicantIdAndAndJobResumeId(int applicantId,int jobResumeId);
    List<WhoInterested> findWhoInterestedByJobResumeId(int id);

}
