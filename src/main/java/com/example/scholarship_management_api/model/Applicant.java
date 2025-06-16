package com.example.scholarship_management_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonProperty("scholarship_id")
    private Long scholarshipId;

    @NotBlank
    @JsonProperty("applicant_name")
    private String applicantName;

    @DecimalMin("0.00")
    @DecimalMax("4.00")
    private Double gpa;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;

    private Boolean isDeleted = false;
}
