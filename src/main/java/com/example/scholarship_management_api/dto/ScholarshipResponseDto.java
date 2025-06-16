package com.example.scholarship_management_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ScholarshipResponseDto {
    private Long id;

    @JsonProperty("scholarship_name")
    private String scholarshipName;

    @JsonProperty("open_date")
    private LocalDate openDate;

    @JsonProperty("close_date")
    private LocalDate closeDate;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("updated_at")
    private LocalDate updatedAt;

    @JsonProperty("deleted_at")
    private LocalDate deletedAt;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
