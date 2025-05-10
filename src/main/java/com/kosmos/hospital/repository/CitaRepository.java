package com.kosmos.hospital.repository;

import com.kosmos.hospital.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Modifying
    @Query("UPDATE Cita c SET c.activa = false WHERE c.id = :id")
    void cancelarCita(@Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Cita c WHERE c.consultorio.id = :consultorioId AND c.horario = :horario")
    boolean existsByConsultorioAndHorario(Long consultorioId, LocalDateTime horario);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Cita c WHERE c.doctor.id = :doctorId AND c.horario = :horario")
    boolean existsByDoctorAndHorario(Long doctorId, LocalDateTime horario);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Cita c WHERE c.nombrePaciente = :nombrePaciente AND c.horario = :horario")
    boolean existsByNombrePacienteAndHorario(String nombrePaciente, LocalDateTime horario);

    @Query("SELECT c FROM Cita c WHERE c.nombrePaciente = :nombrePaciente AND c.horario BETWEEN :startDate AND :endDate")
    List<Cita> findByNombrePacienteAndHorarioBetween(String nombrePaciente, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT COUNT(c) FROM Cita c WHERE c.doctor.id = :doctorId AND c.horario BETWEEN :startDate AND :endDate")
    long countByDoctorAndHorarioBetween(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);
}
