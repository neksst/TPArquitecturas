package com.TPE.msViaje.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idViaje;

    private Long monopatinId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private double kilometrosRecorridos;
    private boolean enCurso;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("viaje")
    private List<Pausa> pausas;

    public double getTiempoUso() {
        if (fechaInicio != null && fechaFin != null) {
            // Calcula la duración en minutos (puedes ajustarlo según tus necesidades)
            return Duration.between(fechaInicio, fechaFin).toMinutes();
        }
        return 0; // Retorna 0 si alguna de las fechas es nula
    }


}
