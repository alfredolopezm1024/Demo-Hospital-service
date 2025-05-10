package com.kosmos.hospital.dto.cita;

import com.kosmos.hospital.config.HorarioFuturo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaRequestDto {

    @NotNull(message = "El ID del doctor es obligatorio.")
    private Long idDoctor;

    @NotNull(message = "El ID del consultorio es obligatorio.")
    private Long idConsultorio;

    @NotNull(message = "El horario de la cita es obligatorio.")
    @HorarioFuturo(message = "La cita debe concertarse previamente con un minimo de 2 horas.")
    private LocalDateTime horario;

    @NotEmpty(message = "El nombre del paciente es obligatorio.")
    private String nombrePaciente;

}
