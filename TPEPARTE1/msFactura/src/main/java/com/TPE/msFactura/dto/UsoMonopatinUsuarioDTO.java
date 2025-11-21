package com.TPE.msFactura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsoMonopatinUsuarioDTO {
    private Long usuarioId;
    private long tiempoTotalUso;
}
