package com.example.scholarship_management_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
public class ApplicantResponseDto {

    private Long id;

    @JsonProperty("scholarship_id")
    private Long scholarshipId;

    @JsonProperty("applicant_name")
    private String applicantName;

    private Double gpa;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("updated_at")
    private LocalDate updatedAt;

    @JsonProperty("deleted_at")
    private LocalDate deletedAt;
}