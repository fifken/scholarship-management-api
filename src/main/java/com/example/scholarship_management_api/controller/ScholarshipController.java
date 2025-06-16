package com.example.scholarship_management_api.controller;


import com.example.scholarship_management_api.dto.ScholarshipRequestDto;
import com.example.scholarship_management_api.dto.ScholarshipResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scholarship_management_api.model.Applicant;
import com.example.scholarship_management_api.model.Scholarship;
import com.example.scholarship_management_api.service.ScholarshipService;
import java.util.List;


@RestController
@RequestMapping("/scholarships")
@Validated
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    public ScholarshipController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    @PostMapping
    public ResponseEntity<ScholarshipResponseDto> createScholarship(@RequestBody @Validated ScholarshipRequestDto dto) {
        ScholarshipResponseDto savedScholarship = scholarshipService.createScholarship(dto);
        return ResponseEntity.ok(savedScholarship);
    }

    @GetMapping
    public ResponseEntity<List<ScholarshipResponseDto>> getAllScholarships() {
        return ResponseEntity.ok(scholarshipService.getAllScholarships());
    }

    @GetMapping("/{id}/applicants")
    public ResponseEntity<List<Applicant>> getApplicantsByScholarshipId(@PathVariable Long id) {
        List<Applicant> applicants = scholarshipService.getApplicantsByScholarshipId(id);
        return ResponseEntity.ok(applicants);
    }
}
