package com.TPE.msMonopatin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class monopatinDTO {

    private double bateria;
    private double latitud;
    private double longitud;
    private double kilometrosRecorridos;
    private double tiempoUso;
    private Long paradaId;
    private double tarifaBase;
    private double tarifaExtraPausa;

}
