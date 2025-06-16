package com.example.scholarship_management_api.controller;

import com.example.scholarship_management_api.dto.ApplicantRequestDto;
import com.example.scholarship_management_api.dto.ApplicantResponseDto;
import com.example.scholarship_management_api.model.Applicant;
import com.example.scholarship_management_api.service.ApplicantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {


    @Autowired
    private ApplicantService applicantService;

    @PostMapping
    public ApplicantResponseDto createApplicant(@Valid @RequestBody ApplicantRequestDto dto) {
        return applicantService.createApplicant(dto);
    }

    @GetMapping
    public List<ApplicantResponseDto> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    @GetMapping("/{id}")
    public ApplicantResponseDto getApplicantById(@PathVariable Long id) {
        return applicantService.getApplicantById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Applicant> updateApplicant(
            @PathVariable Long id,
            @RequestBody @Valid Applicant updatedApplicant) {
        return ResponseEntity.ok(applicantService.updateApplicant(id, updatedApplicant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Long id) {
        applicantService.softDeleteApplicant(id);
        return ResponseEntity.noContent().build();
    }
}
