package com.kosmos.hospital.config;

import com.kosmos.hospital.model.Consultorio;
import com.kosmos.hospital.model.Doctor;
import com.kosmos.hospital.repository.ConsultorioRepository;
import com.kosmos.hospital.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner dataLoader(ConsultorioRepository consultorioRepository, DoctorRepository doctorRepository) {
        return args -> {
            // Insertar Consultorios
            consultorioRepository.save(Consultorio.builder()
                    .numero(101)
                    .piso("Primer Piso")
                    .build());

            consultorioRepository.save(Consultorio.builder()
                    .numero(102)
                    .piso("Primer Piso")
                    .build());

            consultorioRepository.save(Consultorio.builder()
                    .numero(201)
                    .piso("Segundo Piso")
                    .build());

            consultorioRepository.save(Consultorio.builder()
                    .numero(202)
                    .piso("Segundo Piso")
                    .build());

            consultorioRepository.save(Consultorio.builder()
                    .numero(301)
                    .piso("Tercer Piso")
                    .build());
            // Insertar Doctores

            doctorRepository.save(Doctor.builder()
                    .nombre("Juan")
                    .apellidoPaterno("Pérez")
                    .apellidoMaterno("García")
                    .especialidad("Cardiología")
                    .build());

            doctorRepository.save(Doctor.builder()
                    .nombre("María")
                    .apellidoPaterno("López")
                    .apellidoMaterno("Martínez")
                    .especialidad("Pediatría")
                    .build());

            doctorRepository.save(Doctor.builder()
                    .nombre("Carlos")
                    .apellidoPaterno("Ramírez")
                    .apellidoMaterno("Fernández")
                    .especialidad("Neurología")
                    .build());

            doctorRepository.save(Doctor.builder()
                    .nombre("Ana")
                    .apellidoPaterno("González")
                    .apellidoMaterno("Rodríguez")
                    .especialidad("Médico General")
                    .build());

            doctorRepository.save(Doctor.builder()
                    .nombre("Luis")
                    .apellidoPaterno("Sánchez")
                    .apellidoMaterno("Vázquez")
                    .especialidad("Oftalmología")
                    .build());
        };
    }
}
