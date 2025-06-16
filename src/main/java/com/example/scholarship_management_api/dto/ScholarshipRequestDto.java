package com.example.scholarship_management_api.dto;

import com.example.scholarship_management_api.validation.OpenDateBeforeCloseDate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@OpenDateBeforeCloseDate
public class ScholarshipRequestDto {
    @NotNull
    @Size(min = 3)
    private String scholarshipName;

    @NotNull
    @Future
    private LocalDate openDate;

    @NotNull
    @Future
    private LocalDate closeDate;
}
