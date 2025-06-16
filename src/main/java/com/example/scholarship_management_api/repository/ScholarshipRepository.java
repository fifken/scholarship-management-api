package com.example.scholarship_management_api.repository;

import com.example.scholarship_management_api.model.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    boolean existsByName(String name);
}
