package com.kosmos.hospital.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor extends ModeloBase{

    @Column(length = 250, nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", length = 250, nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 250, nullable = true)
    private String apellidoMaterno;

    @Column(length = 250, nullable = false)
    private String especialidad;
}
