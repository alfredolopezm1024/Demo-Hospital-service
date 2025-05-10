package com.kosmos.hospital.service;

import com.kosmos.hospital.dto.ConsultorioDto;
import com.kosmos.hospital.model.Consultorio;

import java.util.List;

public interface ConsultorioService {

    public List<ConsultorioDto> obtenerTodos();

}
