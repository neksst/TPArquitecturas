package com.TPE.msAdministrador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Long monopatinId;

    private String descripcion;

    private LocalDateTime fechaInicio;

    @Column(nullable = true)
    private LocalDateTime fechaFin;
}
