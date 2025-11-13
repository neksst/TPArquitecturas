package com.TPE.msFactura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;

    private Long viajeId;
    private double tarifaBase;
    private double tarifaExtra;
    private long tiempoUso;
    private long tiempoPausado;
    private double montoCalculado;

}
