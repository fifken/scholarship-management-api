package com.example.scholarship_management_api.repository;

import com.example.scholarship_management_api.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}
