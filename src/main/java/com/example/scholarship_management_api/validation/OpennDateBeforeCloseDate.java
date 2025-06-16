package com.example.scholarship_management_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OpenDateBeforeCloseDateValidator.class)
@Target({ElementType.TYPE})
//-> target: Menentukan di mana anotasi ini boleh dipakai.
//ElementType.TYPE artinya hanya bisa digunakan di level class/interface
// (misalnya di ScholarshipRequestDto), bukan di field.
@Retention(RetentionPolicy.RUNTIME)
public @interface OpennDateBeforeCloseDate {
    String message() default "openDate must be before closeDate"; //Pesan error default jika validasi gagal.
    Class<?>[] groups() default {}; // Untuk grup validasi, biasanya dibiarkan default kecuali kamu ingin validasi berbeda di kondisi tertentu.
    Class<? extends Payload>[] payload() default {}; //Untuk mengirimkan informasi tambahan saat validasi (jarang digunakan, biasanya dibiarkan kosong).
}

//explore penggunaannya gmn