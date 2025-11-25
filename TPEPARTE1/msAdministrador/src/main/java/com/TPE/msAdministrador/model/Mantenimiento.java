package com.TPE.msAdministrador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mantenimientos")
public class Mantenimiento {

    @Id
    private String id;  // Mongo usa String como ID (ObjectId)

    private Long monopatinId;

    private String descripcion;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin; // puede ser null sin @Column
}
