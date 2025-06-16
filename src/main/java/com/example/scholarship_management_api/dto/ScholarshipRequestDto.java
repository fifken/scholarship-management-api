package com.example.scholarship_management_api.dto;

import com.example.scholarship_management_api.validation.OpennDateBeforeCloseDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@OpennDateBeforeCloseDate
public class ScholarshipRequestDto {
    @NotNull
    @Size(min = 3)
    @JsonProperty("scholarship_name")
    private String scholarshipName;

    @NotNull
    @Future
    @JsonProperty("open_date")
    private LocalDate openDate;

    @NotNull
    @Future
    @JsonProperty("close_date")
    private LocalDate closeDate;
}
