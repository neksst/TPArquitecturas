package com.TPE.msViaje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteKilometrosDTO {
    private Long monopatinId;
    private double kilometrosTotales;
}
