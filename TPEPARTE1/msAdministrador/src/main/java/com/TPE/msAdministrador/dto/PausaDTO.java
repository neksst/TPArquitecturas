package com.TPE.msAdministrador.dto;

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
}
