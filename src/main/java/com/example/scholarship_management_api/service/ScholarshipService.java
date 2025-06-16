package com.example.scholarship_management_api.service;

import com.example.scholarship_management_api.dto.ScholarshipRequestDto;
import com.example.scholarship_management_api.dto.ScholarshipResponseDto;
import com.example.scholarship_management_api.model.Applicant;
import com.example.scholarship_management_api.model.Scholarship;
import com.example.scholarship_management_api.repository.ApplicantRepository;
import com.example.scholarship_management_api.repository.ScholarshipRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScholarshipService {
    private final ScholarshipRepository scholarshipRepository;
    private final ApplicantRepository applicantRepository;

    public ScholarshipService(ScholarshipRepository scholarshipRepository, ApplicantRepository applicantRepository) {
        this.scholarshipRepository = scholarshipRepository;
        this.applicantRepository = applicantRepository;
    }

    public List<ScholarshipResponseDto> getAllScholarships() {
        return scholarshipRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    public ScholarshipResponseDto createScholarship(ScholarshipRequestDto dto) {
        Scholarship scholarship = Scholarship.builder()
                .scholarshipName(dto.getScholarshipName())
                .openDate(dto.getOpenDate())
                .closeDate(dto.getCloseDate())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .isDeleted(false)
                .build();
        Scholarship saved = scholarshipRepository.save(scholarship);
        return toResponseDto(saved);
    }

    public Optional<ScholarshipResponseDto> getScholarshipById(Long id) {
        return scholarshipRepository.findById(id)
                .map(this::toResponseDto);
    }

    public List<Applicant> getApplicantsByScholarshipId(Long id) {
        return applicantRepository.findAllByScholarshipId(id);
    }

    private ScholarshipResponseDto toResponseDto(Scholarship scholarship) {
        return ScholarshipResponseDto.builder()
                .id(scholarship.getId())
                .scholarshipName(scholarship.getScholarshipName())
                .openDate(scholarship.getOpenDate())
                .closeDate(scholarship.getCloseDate())
                .createdAt(scholarship.getCreatedAt())
                .updatedAt(scholarship.getUpdatedAt())
                .deletedAt(scholarship.getDeletedAt())
                .isDeleted(scholarship.getIsDeleted())
                .build();
    }
}
