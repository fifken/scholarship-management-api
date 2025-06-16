package com.example.scholarship_management_api.service;

import com.example.scholarship_management_api.dto.ApplicantRequestDto;
import com.example.scholarship_management_api.dto.ApplicantResponseDto;
import com.example.scholarship_management_api.model.Applicant;
import com.example.scholarship_management_api.repository.ApplicantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ApplicantServiceTest {

    @InjectMocks
    private ApplicantService applicantService;

    @Mock
    private ApplicantRepository applicantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateApplicant() {
        ApplicantRequestDto request = new ApplicantRequestDto();
        request.setScholarshipId(1L);
        request.setApplicantName("Jane Doe");
        request.setGpa(3.5);

        Applicant saved = new Applicant();
        saved.setId(1L);
        saved.setScholarshipId(1L);
        saved.setApplicantName("Jane Doe");
        saved.setGpa(3.5);
        saved.setCreatedAt(LocalDate.now());
        saved.setIsDeleted(false);

        when(applicantRepository.save(any(Applicant.class))).thenReturn(saved);

        ApplicantResponseDto response = applicantService.createApplicant(request);

        assertNotNull(response);
        assertEquals("Jane Doe", response.getApplicantName());
        verify(applicantRepository, times(1)).save(any(Applicant.class));
    }

    @Test
    void testGetAllApplicants() {
        Applicant a1 = new Applicant();
        a1.setId(1L);
        a1.setApplicantName("A");
        a1.setScholarshipId(1L);
        a1.setGpa(3.0);
        a1.setIsDeleted(false);

        Applicant a2 = new Applicant();
        a2.setId(2L);
        a2.setApplicantName("B");
        a2.setScholarshipId(2L);
        a2.setGpa(3.5);
        a2.setIsDeleted(false);

        when(applicantRepository.findAll()).thenReturn(List.of(a1, a2));

        List<ApplicantResponseDto> result = applicantService.getAllApplicants();

        assertEquals(2, result.size());
        verify(applicantRepository).findAll();
    }

    @Test
    void testGetApplicantById_Found() {
        Applicant applicant = new Applicant();
        applicant.setId(1L);
        applicant.setApplicantName("Test");
        applicant.setScholarshipId(1L);
        applicant.setGpa(3.8);
        applicant.setIsDeleted(false);

        when(applicantRepository.findById(1L)).thenReturn(Optional.of(applicant));

        ApplicantResponseDto dto = applicantService.getApplicantById(1L);

        assertEquals("Test", dto.getApplicantName());
        assertEquals(3.8, dto.getGpa());
    }

    @Test
    void testGetApplicantById_NotFound() {
        when(applicantRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            applicantService.getApplicantById(1L);
        });
    }

    @Test
    void testUpdateApplicant_Success() {
        Applicant existing = new Applicant();
        existing.setId(1L);
        existing.setIsDeleted(false);

        Applicant update = new Applicant();
        update.setApplicantName("Updated");
        update.setScholarshipId(99L);
        update.setGpa(3.3);

        when(applicantRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(applicantRepository.save(any(Applicant.class))).thenAnswer(i -> i.getArgument(0));

        Applicant result = applicantService.updateApplicant(1L, update);

        assertEquals("Updated", result.getApplicantName());
        assertEquals(99L, result.getScholarshipId());
        assertEquals(3.3, result.getGpa());
    }

    @Test
    void testUpdateApplicant_Deleted() {
        Applicant existing = new Applicant();
        existing.setId(1L);
        existing.setIsDeleted(true);

        when(applicantRepository.findById(1L)).thenReturn(Optional.of(existing));

        assertThrows(RuntimeException.class, () -> {
            applicantService.updateApplicant(1L, new Applicant());
        });
    }

    @Test
    void testSoftDeleteApplicant_Success() {
        Applicant existing = new Applicant();
        existing.setId(1L);
        existing.setIsDeleted(false);

        when(applicantRepository.findById(1L)).thenReturn(Optional.of(existing));

        applicantService.softDeleteApplicant(1L);

        assertTrue(existing.getIsDeleted());
        verify(applicantRepository).save(existing);
    }

    @Test
    void testSoftDeleteApplicant_AlreadyDeleted() {
        Applicant existing = new Applicant();
        existing.setId(1L);
        existing.setIsDeleted(true);

        when(applicantRepository.findById(1L)).thenReturn(Optional.of(existing));

        assertThrows(RuntimeException.class, () -> {
            applicantService.softDeleteApplicant(1L);
        });
    }
}