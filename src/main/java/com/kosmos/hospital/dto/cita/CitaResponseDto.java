package com.kosmos.hospital.dto.cita;

import com.kosmos.hospital.dto.ConsultorioDto;
import com.kosmos.hospital.dto.DoctorDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaResponseDto {

    private ConsultorioDto consultorio;
    private DoctorDto doctor;
    private LocalDateTime horario;
    private String nombrePaciente;
    private Boolean activa;

}
