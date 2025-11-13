package com.TPE.msAdministrador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteKilometrosDTO {
    private Long monopatinId;
    private double kilometrosRecorridos;
    private double tiempoPausado;
}
