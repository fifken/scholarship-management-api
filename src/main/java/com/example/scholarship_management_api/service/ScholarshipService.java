package com.example.scholarship_management_api.service;

import com.example.scholarship_management_api.model.Scholarship;
import com.example.scholarship_management_api.repository.ScholarshipRepository;

import java.util.List;
import java.util.Optional;

public class ScholarshipService {
    private final ScholarshipRepository scholarshipRepository;

    public ScholarshipService(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll();
    }

    public Scholarship addScholarship(Scholarship scholarship) {
        return scholarshipRepository.save(scholarship);
    }

    public Optional<Scholarship> getScholarshipById(Long id) { // using Optional for better null handling
        return scholarshipRepository.findById(id);
    }
}
