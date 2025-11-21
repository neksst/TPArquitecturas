package com.TPE.msAdministrador.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservicio-factura",contextId = "facturaClient", url = "http://localhost:9004")
public interface FacturaClient {

    @GetMapping("/api/factura/total-facturado")
    Double obtenerTotalFacturado(
            @RequestParam int anio,
            @RequestParam int mesInicio,
            @RequestParam int mesFin
    );

}
