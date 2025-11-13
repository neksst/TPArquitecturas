package com.TPE.msMonopatin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Monopatin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idMonopatin;

    private double bateria;
    private boolean disponible;
    private boolean enMantenimiento;
    private double latitud;
    private double longitud;
    private double kilometrosRecorridos;
    private double tiempoUso;

    @ManyToOne
    @JoinColumn(name = "parada_id", nullable = false)
    private Parada parada;



}
