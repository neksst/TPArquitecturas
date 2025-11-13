package com.TPE.msAdministrador.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@FeignClient(name = "microservicio-factura", url = "http://localhost:9004")
@RequestMapping("/api/tarifa")
public interface TarifaClient {

    @PostMapping("/ajustar-precios")
    void ajustarPrecios(double nuevaTarifaBase, double nuevaTarifaExtra, LocalDate fechaInicio);

}
