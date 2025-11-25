package com.TPE.msAdministrador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {

    private Long id;

    private Long monopatinId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private double kilometrosRecorridos;
    private boolean enCurso;

    private List<PausaDTO> pausas;

    public double getTiempoUso() {
        if (fechaInicio != null && fechaFin != null) {
            return Duration.between(fechaInicio, fechaFin).toMinutes();
        }
        return 0;
    }
}
