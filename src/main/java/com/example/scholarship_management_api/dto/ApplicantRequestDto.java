package com.example.scholarship_management_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ApplicantRequestDto {

    @NotNull
    @JsonProperty("scholarship_id")
    private Long scholarshipId;

    @NotBlank
    @Size(min = 3)
    @JsonProperty("applicant_name")
    private String applicantName;

    @DecimalMin("0.00")
    @DecimalMax("4.00")
    private Double gpa;

}