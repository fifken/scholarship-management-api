package com.example.scholarship_management_api.controller;

import com.example.scholarship_management_api.dto.ApplicantRequestDto;
import com.example.scholarship_management_api.dto.ApplicantResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.scholarship_management_api.service.ApplicantService;

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
}