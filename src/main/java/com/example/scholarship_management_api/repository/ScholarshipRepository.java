package com.example.scholarship_management_api.repository;

import com.example.scholarship_management_api.model.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
//    List <Scholarship> findByApplicantsId(Long applicantId);
}
