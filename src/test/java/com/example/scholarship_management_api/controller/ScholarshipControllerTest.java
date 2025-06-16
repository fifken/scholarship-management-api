package com.example.scholarship_management_api.controller;

import com.example.scholarship_management_api.dto.ScholarshipRequestDto;
import com.example.scholarship_management_api.dto.ScholarshipResponseDto;
import com.example.scholarship_management_api.model.Applicant;
import com.example.scholarship_management_api.service.ScholarshipService;
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
class ScholarshipControllerTest {

    @Mock
    private ScholarshipService scholarshipService;

    @InjectMocks
    private ScholarshipController scholarshipController;

    private ScholarshipResponseDto scholarshipDto;

    @BeforeEach
    void setUp() {
        scholarshipDto = ScholarshipResponseDto.builder()
                .id(1L)
                .scholarshipName("Tech Scholarship")
                .openDate(LocalDate.now())
                .closeDate(LocalDate.now().plusDays(30))
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .isDeleted(false)
                .build();
    }

    @Test
    void testCreateScholarship() {
        ScholarshipRequestDto requestDto = new ScholarshipRequestDto();
        requestDto.setScholarshipName("Tech Scholarship");
        requestDto.setOpenDate(LocalDate.now());
        requestDto.setCloseDate(LocalDate.now().plusDays(30));

        Mockito.when(scholarshipService.createScholarship(any(ScholarshipRequestDto.class))).thenReturn(scholarshipDto);

        ResponseEntity<ScholarshipResponseDto> response = scholarshipController.createScholarship(requestDto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Tech Scholarship", response.getBody().getScholarshipName());
    }

    @Test
    void testGetAllScholarships() {
        Mockito.when(scholarshipService.getAllScholarships()).thenReturn(List.of(scholarshipDto));

        ResponseEntity<List<ScholarshipResponseDto>> response = scholarshipController.getAllScholarships();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Tech Scholarship", response.getBody().get(0).getScholarshipName());
    }

    @Test
    void testGetApplicantsByScholarshipId() {
        Applicant applicant = new Applicant();
        applicant.setId(1L);
        applicant.setApplicantName("John Doe");

        Mockito.when(scholarshipService.getApplicantsByScholarshipId(1L)).thenReturn(List.of(applicant));

        ResponseEntity<List<Applicant>> response = scholarshipController.getApplicantsByScholarshipId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getApplicantName());
    }
}