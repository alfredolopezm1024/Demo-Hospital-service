package com.kosmos.hospital.controller;

import com.kosmos.hospital.dto.cita.CitaDto;
import com.kosmos.hospital.dto.cita.CitaRequestDto;
import com.kosmos.hospital.model.Cita;
import com.kosmos.hospital.service.CitasService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
@AllArgsConstructor
public class CitasController {

    private final CitasService citasService;

    @PostMapping
    public ResponseEntity<String> crearCita(@RequestBody @Valid CitaRequestDto citaRequest){

        citasService.crearCita(citaRequest);

        return new ResponseEntity<>("Cita agendada", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable Long id){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id) {
            citasService.cancelarCita(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDto> modificarCita(@PathVariable Long id, @Valid @RequestBody CitaDto citaDto) {
            CitaDto updatedCita = citasService.modificarCita(id, citaDto);
            return new ResponseEntity<>(updatedCita, HttpStatus.OK);
    }


}
