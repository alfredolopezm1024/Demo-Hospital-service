package com.kosmos.hospital.service;

import com.kosmos.hospital.dto.cita.CitaDto;
import com.kosmos.hospital.dto.cita.CitaRequestDto;
import com.kosmos.hospital.dto.cita.CitaResponseDto;

import java.util.List;

public interface CitasService {

    public void crearCita(CitaRequestDto citaRequest);

    public List<CitaResponseDto> obtenerCitasPorFiltro(CitaRequestDto citaRequestDto);

    public void cancelarCita(Long id);

    public CitaDto modificarCita(Long id, CitaDto citaDto);

}
