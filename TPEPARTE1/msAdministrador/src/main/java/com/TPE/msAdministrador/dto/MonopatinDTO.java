package com.TPE.msAdministrador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinDTO {

    private Long idMonopatin;

    private double bateria;
    private boolean disponible;
    private boolean enMantenimiento;
    private double latitud;
    private double longitud;
    private double kilometrosRecorridos;
    private double tiempoUso;

}
