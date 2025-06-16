package com.example.scholarship_management_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @JsonProperty("scholarship_name")
    private String scholarshipName;

    @Future
    @JsonProperty("open_date")
    private LocalDate openDate;

    @Future
    @JsonProperty("close_date")
    private LocalDate closeDate;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;

    private Boolean isDeleted = false;

//    @OneToMany(mappedBy = "scholarship", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Applicant> applicants;

}
