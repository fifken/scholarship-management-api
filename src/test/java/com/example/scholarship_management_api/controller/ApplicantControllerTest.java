package com.example.scholarship_management_api.controller;

import com.example.scholarship_management_api.dto.ApplicantRequestDto;
import com.example.scholarship_management_api.dto.ApplicantResponseDto;
import com.example.scholarship_management_api.model.Applicant;
import com.example.scholarship_management_api.service.ApplicantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class ApplicantControllerTest {

    @Mock
    private ApplicantService applicantService;

    @InjectMocks
    private ApplicantController applicantController;

    private ApplicantResponseDto sampleResponse;

    @BeforeEach
    void setUp() {
        sampleResponse = new ApplicantResponseDto();
        sampleResponse.setId(1L);
        sampleResponse.setScholarshipId(101L);
        sampleResponse.setApplicantName("John Doe");
        sampleResponse.setGpa(3.5);
        sampleResponse.setCreatedAt(LocalDate.now());
    }

    @Test
    void testCreateApplicant() {
        ApplicantRequestDto requestDto = new ApplicantRequestDto();
        requestDto.setApplicantName("John Doe");
        requestDto.setScholarshipId(101L);
        requestDto.setGpa(3.5);

        Mockito.when(applicantService.createApplicant(any(ApplicantRequestDto.class))).thenReturn(sampleResponse);

        ApplicantResponseDto response = applicantController.createApplicant(requestDto);

        assertNotNull(response);
        assertEquals("John Doe", response.getApplicantName());
    }

    @Test
    void testGetAllApplicants() {
        Mockito.when(applicantService.getAllApplicants()).thenReturn(List.of(sampleResponse));

        List<ApplicantResponseDto> result = applicantController.getAllApplicants();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getApplicantName());
    }

    @Test
    void testGetApplicantById() {
        Mockito.when(applicantService.getApplicantById(1L)).thenReturn(sampleResponse);

        ApplicantResponseDto result = applicantController.getApplicantById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getApplicantName());
    }

    @Test
    void testUpdateApplicant() {
        Applicant updated = new Applicant();
        updated.setId(1L);
        updated.setScholarshipId(101L);
        updated.setApplicantName("Jane Updated");
        updated.setGpa(3.8);

        Mockito.when(applicantService.updateApplicant(eq(1L), any(Applicant.class))).thenReturn(updated);

        ResponseEntity<Applicant> response = applicantController.updateApplicant(1L, updated);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Jane Updated", response.getBody().getApplicantName());
    }

    @Test
    void testSoftDeleteApplicant() {
        ResponseEntity<Void> response = applicantController.deleteApplicant(1L);

        assertEquals(204, response.getStatusCodeValue());
        Mockito.verify(applicantService).softDeleteApplicant(1L);
    }
}