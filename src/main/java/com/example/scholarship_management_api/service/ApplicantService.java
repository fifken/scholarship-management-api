package com.example.scholarship_management_api.service;

import com.example.scholarship_management_api.dto.ApplicantRequestDto;
import com.example.scholarship_management_api.dto.ApplicantResponseDto;
import com.example.scholarship_management_api.model.Applicant;
import com.example.scholarship_management_api.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public ApplicantResponseDto createApplicant(ApplicantRequestDto dto) {
        Applicant applicant = new Applicant();
        applicant.setScholarshipId(dto.getScholarshipId());
        applicant.setApplicantName(dto.getApplicantName());
        applicant.setGpa(dto.getGpa());
        applicant.setCreatedAt(LocalDate.now());
        applicant.setIsDeleted(false);

        Applicant saved = applicantRepository.save(applicant);

        return mapToResponseDto(saved);
    }

    public List<ApplicantResponseDto> getAllApplicants() {
        return applicantRepository.findAll().stream()
                .filter(applicant -> !Boolean.TRUE.equals(applicant.getIsDeleted()))
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public ApplicantResponseDto getApplicantById(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .filter(a -> !Boolean.TRUE.equals(a.getIsDeleted()))
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        return mapToResponseDto(applicant);
    }

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

    private ApplicantResponseDto mapToResponseDto(Applicant applicant) {
        ApplicantResponseDto dto = new ApplicantResponseDto();
        dto.setId(applicant.getId());
        dto.setScholarshipId(applicant.getScholarshipId());
        dto.setApplicantName(applicant.getApplicantName());
        dto.setGpa(applicant.getGpa());
        dto.setCreatedAt(applicant.getCreatedAt());
        dto.setUpdatedAt(applicant.getUpdatedAt());
        return dto;
    }
}
