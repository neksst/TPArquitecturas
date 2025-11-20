package com.TPE.msViaje.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pausa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPausa;

    private LocalDateTime inicio;
    private LocalDateTime fin;
    private double duracion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idViaje", nullable = false)
    private Viaje viaje;

}
