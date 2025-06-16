package com.example.scholarship_management_api.controller;

import com.example.scholarship_management_api.model.Applicant;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.scholarship_management_api.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

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
