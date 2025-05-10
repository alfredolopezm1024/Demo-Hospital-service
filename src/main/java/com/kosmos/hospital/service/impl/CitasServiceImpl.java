package com.kosmos.hospital.service.impl;

import com.kosmos.hospital.dto.cita.CitaDto;
import com.kosmos.hospital.dto.cita.CitaRequestDto;
import com.kosmos.hospital.dto.cita.CitaResponseDto;
import com.kosmos.hospital.model.Cita;
import com.kosmos.hospital.model.Consultorio;
import com.kosmos.hospital.model.Doctor;
import com.kosmos.hospital.repository.CitaRepository;
import com.kosmos.hospital.repository.ConsultorioRepository;
import com.kosmos.hospital.repository.DoctorRepository;
import com.kosmos.hospital.service.CitasService;
import com.kosmos.hospital.utils.MapperUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitasServiceImpl implements CitasService{

    private final CitaRepository citaRepository;
    private final DoctorRepository doctorRepository;
    private final ConsultorioRepository consultorioRepository;

    @Override
    public void crearCita(CitaRequestDto citaRequest) {
        // Verifica si el doctor existe
        Doctor doctor = doctorRepository.findById(citaRequest.getIdDoctor())
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));

        // Verifica si el consultorio existe
        Consultorio consultorio = consultorioRepository.findById(citaRequest.getIdConsultorio())
                .orElseThrow(() -> new RuntimeException("Consultorio no encontrado"));

        boolean existeCitaEnConsultorio = citaRepository.existsByConsultorioAndHorario(consultorio.getId(), citaRequest.getHorario());
        if (existeCitaEnConsultorio) {
            throw new IllegalStateException("Ya existe una cita agendada en este consultorio a esta hora.");
        }

        // Verificar que no haya citas para el mismo doctor a la misma hora
        boolean existeCitaEnDoctor = citaRepository.existsByDoctorAndHorario(doctor.getId(), citaRequest.getHorario());
        if (existeCitaEnDoctor) {
            throw new IllegalStateException("Ya existe una cita agendada para este doctor a esta hora.");
        }

        // Verificar que el mismo paciente no tenga más de una cita a la misma hora
        boolean existeCitaPaciente = citaRepository.existsByNombrePacienteAndHorario(citaRequest.getNombrePaciente(), citaRequest.getHorario());
        if (existeCitaPaciente) {
            throw new IllegalStateException("El paciente ya tiene una cita agendada a esta hora.");
        }

        // Verificar que el paciente tenga al menos 2 horas de diferencia si tiene más de una cita el mismo día
        LocalDateTime inicioDelDia = citaRequest.getHorario().toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = citaRequest.getHorario().toLocalDate().atTime(23, 59);
        List<Cita> citasDelPaciente = citaRepository.findByNombrePacienteAndHorarioBetween(citaRequest.getNombrePaciente(), inicioDelDia, finDelDia);
        for (Cita cita : citasDelPaciente) {
            if (Math.abs(citaRequest.getHorario().getHour() - cita.getHorario().getHour()) < 2) {
                throw new IllegalStateException("El paciente debe tener al menos 2 horas de diferencia entre citas en el mismo día.");
            }
        }

        // Verificar que el doctor no tenga más de 8 citas en el mismo día
        long citasDelDoctorEnElDia = citaRepository.countByDoctorAndHorarioBetween(doctor.getId(), inicioDelDia, finDelDia);
        if (citasDelDoctorEnElDia >= 8) {
            throw new IllegalStateException("El doctor no puede tener más de 8 citas al día.");
        }

        Cita cita = Cita.builder()
                .doctor(doctor)
                .consultorio(consultorio)
                .horario(citaRequest.getHorario())
                .nombrePaciente(citaRequest.getNombrePaciente())
                .build();

        citaRepository.save(cita);
    }

    @Transactional
    @Override
    public void cancelarCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));

        // Verificar si la cita está activa
        if (!cita.getActiva()) {
            throw new IllegalStateException("La cita ya ha sido cancelada.");
        }

        // Verificar si la cita ya ocurrió
        if (cita.getHorario().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("La cita ya ha ocurrido y no puede ser cancelada.");
        }

        cita.setActiva(false);

        citaRepository.save(cita);
    }

    @Override
    public CitaDto modificarCita(Long id, CitaDto citaDto) {
        // Buscar la cita
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con ID: " + id));

        // Verificar si la cita está activa
        if (!cita.getActiva()) {
            throw new IllegalStateException("La cita ya ha sido cancelada y no puede ser modificada.");
        }

        // Verificar si la cita ya ocurrió
        if (cita.getHorario().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("La cita ya ha ocurrido y no puede ser modificada.");
        }

        // Modificar los campos
        cita.setHorario(citaDto.getHorario());
        cita.setNombrePaciente(cita.getNombrePaciente());

        // Guardar los cambios
        Cita citaModificada= citaRepository.save(cita);

        // Retorna el resultado en el DTO
        return MapperUtils.map(citaModificada, CitaDto.class);
    }

    @Override
    public List<CitaResponseDto> obtenerCitasPorFiltro(CitaRequestDto citaRequestDto) {
        return List.of();
    }
}
