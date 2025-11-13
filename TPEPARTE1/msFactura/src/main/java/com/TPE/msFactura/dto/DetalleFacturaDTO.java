package com.TPE.msFactura.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFacturaDTO {
    private Long facturaId;
    private Long viajeId;
    private double tarifaBase;
    private double tarifaExtra;
    private long tiempoUso;
    private long tiempoPausado;
}
