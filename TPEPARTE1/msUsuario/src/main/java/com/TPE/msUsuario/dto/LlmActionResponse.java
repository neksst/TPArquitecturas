package com.TPE.msUsuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LlmActionResponse {
    private String accion; // e.g. "obtener_precio", "obtener_distancia", "monopatines_cercanos", "movimientos_cuenta"
    private Map<String, Object> parametros; // pares clave-valor: {"origen":"Cordoba","destino":"Carlos Paz"}
}
