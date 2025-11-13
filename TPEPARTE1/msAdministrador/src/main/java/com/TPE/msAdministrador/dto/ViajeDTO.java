package com.TPE.msAdministrador.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "viajeDTO", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("viajeDTO")
    private List<PausaDTO> pausas;

    public double getTiempoUso() {
        if (fechaInicio != null && fechaFin != null) {
            // Calcula la duración en minutos (puedes ajustarlo según tus necesidades)
            return Duration.between(fechaInicio, fechaFin).toMinutes();
        }
        return 0; // Retorna 0 si alguna de las fechas es nula
    }
}
