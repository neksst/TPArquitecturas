package com.TPE.msFactura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarifa {
    public enum TipoTarifa {
        BASE,
        EXTRA_PAUSA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTarifa tipo;

    private double monto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Tarifa(TipoTarifa tipo, double monto, LocalDate fechaInicio, LocalDate fechaFin) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}
