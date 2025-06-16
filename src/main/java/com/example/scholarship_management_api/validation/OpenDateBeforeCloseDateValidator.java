package com.example.scholarship_management_api.validation;

import com.example.scholarship_management_api.dto.ScholarshipRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class OpenDateBeforeCloseDateValidator implements ConstraintValidator<OpenDateBeforeCloseDate, ScholarshipRequestDto> {

    @Override
    public boolean isValid(ScholarshipRequestDto dto, ConstraintValidatorContext context) {
        if (dto.getOpenDate() == null || dto.getCloseDate() == null) {
            return true; // Handled by @NotNull
        }
        return dto.getOpenDate().isBefore(dto.getCloseDate());
    }
}