package com.kosmos.hospital.service.impl;

import com.kosmos.hospital.dto.ConsultorioDto;
import com.kosmos.hospital.dto.DoctorDto;
import com.kosmos.hospital.repository.ConsultorioRepository;
import com.kosmos.hospital.repository.DoctorRepository;
import com.kosmos.hospital.service.ConsultorioService;
import com.kosmos.hospital.utils.MapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConsultorioServiceImpl implements ConsultorioService {

    private final ConsultorioRepository consultorioRepository;

    @Override
    public List<ConsultorioDto> obtenerTodos() {
        return MapperUtils.mapList(consultorioRepository.findAll(), ConsultorioDto.class);
    }
}
