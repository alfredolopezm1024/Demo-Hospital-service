package com.kosmos.hospital.controller;

import com.kosmos.hospital.dto.DoctorDto;
import com.kosmos.hospital.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorDto>> obtenerTodos(){
        return new ResponseEntity<>(doctorService.obtenerTodos(), HttpStatus.OK);
    }

}
