package com.kosmos.hospital.service.impl;

import com.kosmos.hospital.dto.DoctorDto;
import com.kosmos.hospital.repository.DoctorRepository;
import com.kosmos.hospital.service.DoctorService;
import com.kosmos.hospital.utils.MapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public List<DoctorDto> obtenerTodos() {
        return MapperUtils.mapList(doctorRepository.findAll(), DoctorDto.class);
    }
}
