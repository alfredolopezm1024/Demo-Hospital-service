package com.kosmos.hospital.config;

import com.kosmos.hospital.utils.HorarioFuturoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = HorarioFuturoValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface HorarioFuturo {
    String message() default "El horario debe ser al menos dos horas en el futuro.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
