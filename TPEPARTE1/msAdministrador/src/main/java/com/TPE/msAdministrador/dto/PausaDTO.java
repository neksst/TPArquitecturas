package com.TPE.msAdministrador.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PausaDTO {
    private Long id;

    private LocalDateTime inicio;
    private LocalDateTime fin;
    private double duracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viajeDTO_id", nullable = false)
    private ViajeDTO viaje;
}
