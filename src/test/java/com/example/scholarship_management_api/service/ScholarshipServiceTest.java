package com.example.scholarship_management_api.service;

import com.example.scholarship_management_api.dto.ScholarshipRequestDto;
import com.example.scholarship_management_api.dto.ScholarshipResponseDto;
import com.example.scholarship_management_api.model.Applicant;
import com.example.scholarship_management_api.model.Scholarship;
import com.example.scholarship_management_api.repository.ApplicantRepository;
import com.example.scholarship_management_api.repository.ScholarshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScholarshipServiceTest {

    @Mock
    private ScholarshipRepository scholarshipRepository;

    @Mock
    private ApplicantRepository applicantRepository;

    @InjectMocks
    private ScholarshipService scholarshipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllScholarships() {
        Scholarship s1 = Scholarship.builder()
                .id(1L)
                .scholarshipName("Beasiswa A")
                .openDate(LocalDate.of(2025, 1, 1))
                .closeDate(LocalDate.of(2025, 2, 1))
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .isDeleted(false)
                .build();

        when(scholarshipRepository.findAll()).thenReturn(List.of(s1));

        List<ScholarshipResponseDto> result = scholarshipService.getAllScholarships();

        assertEquals(1, result.size());
        assertEquals("Beasiswa A", result.get(0).getScholarshipName());
    }

    @Test
    void testCreateScholarship() {
        ScholarshipRequestDto dto = new ScholarshipRequestDto();
        dto.setScholarshipName("Beasiswa B");
        dto.setOpenDate(LocalDate.of(2025, 3, 1));
        dto.setCloseDate(LocalDate.of(2025, 4, 1));

        Scholarship saved = Scholarship.builder()
                .id(2L)
                .scholarshipName("Beasiswa B")
                .openDate(dto.getOpenDate())
                .closeDate(dto.getCloseDate())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .isDeleted(false)
                .build();

        when(scholarshipRepository.save(any(Scholarship.class))).thenReturn(saved);

        ScholarshipResponseDto response = scholarshipService.createScholarship(dto);

        assertNotNull(response);
        assertEquals("Beasiswa B", response.getScholarshipName());
        verify(scholarshipRepository, times(1)).save(any(Scholarship.class));
    }

    @Test
    void testGetScholarshipById_Found() {
        Scholarship s = Scholarship.builder()
                .id(3L)
                .scholarshipName("Beasiswa C")
                .openDate(LocalDate.of(2025, 5, 1))
                .closeDate(LocalDate.of(2025, 6, 1))
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .isDeleted(false)
                .build();

        when(scholarshipRepository.findById(3L)).thenReturn(Optional.of(s));

        Optional<ScholarshipResponseDto> result = scholarshipService.getScholarshipById(3L);

        assertTrue(result.isPresent());
        assertEquals("Beasiswa C", result.get().getScholarshipName());
    }

    @Test
    void testGetScholarshipById_NotFound() {
        when(scholarshipRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<ScholarshipResponseDto> result = scholarshipService.getScholarshipById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void testGetApplicantsByScholarshipId() {
        Long scholarshipId = 1L;

        Applicant a1 = new Applicant();
        a1.setId(1L);
        a1.setScholarshipId(scholarshipId);
        a1.setApplicantName("Alice");

        Applicant a2 = new Applicant();
        a2.setId(2L);
        a2.setScholarshipId(scholarshipId);
        a2.setApplicantName("Bob");

        when(applicantRepository.findAllByScholarshipId(scholarshipId))
                .thenReturn(List.of(a1, a2));

        List<Applicant> result = scholarshipService.getApplicantsByScholarshipId(scholarshipId);

        assertEquals(2, result.size());
        verify(applicantRepository).findAllByScholarshipId(scholarshipId);
    }
}