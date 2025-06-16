package com.example.scholarship_management_api.service;

import com.example.scholarship_management_api.model.Applicant;
import com.example.scholarship_management_api.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public Applicant updateApplicant(Long id, Applicant updatedApplicant) {
        Applicant existing = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        if (Boolean.TRUE.equals(existing.getIsDeleted())) {
            throw new RuntimeException("Cannot update a deleted applicant");
        }

        existing.setApplicantName(updatedApplicant.getApplicantName());
        existing.setScholarshipId(updatedApplicant.getScholarshipId());
        existing.setGpa(updatedApplicant.getGpa());
        existing.setUpdatedAt(LocalDate.now());

        return applicantRepository.save(existing);
    }

    public void softDeleteApplicant(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        if (Boolean.TRUE.equals(applicant.getIsDeleted())) {
            throw new RuntimeException("Applicant already deleted");
        }

        applicant.setIsDeleted(true);
        applicant.setDeletedAt(LocalDate.now());
        applicantRepository.save(applicant);
    }

}
