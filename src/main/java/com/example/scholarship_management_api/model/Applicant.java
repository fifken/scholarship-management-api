package com.example.scholarship_management_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.NotFound;

import java.time.LocalDate;

@Entity
@Data
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("scholarship_id")
    private Long scholarshipId;

    @NotBlank
    @Size(min = 3)
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
