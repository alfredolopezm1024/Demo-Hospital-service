package com.kosmos.hospital.utils;

import com.kosmos.hospital.config.HorarioFuturo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class HorarioFuturoValidator implements ConstraintValidator<HorarioFuturo, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime horario, ConstraintValidatorContext context) {
        if (horario == null) {
            return true;
        }
        return horario.isAfter(LocalDateTime.now().plusHours(2));
    }
}
