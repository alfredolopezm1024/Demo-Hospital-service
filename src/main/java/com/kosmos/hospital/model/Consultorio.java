package com.kosmos.hospital.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consultorio extends ModeloBase{

    @Column(nullable = false)
    private Integer numero;

    @Column(length = 50, nullable = false)
    private String piso;

}
