package com.TPE.msAdministrador.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "microservicio-factura",contextId = "tarifaClient", url = "http://localhost:9004")
public interface TarifaClient {

    @PostMapping("/api/tarifa/ajustar-precios")
    void ajustarPrecios(@RequestParam double nuevaTarifaBase,@RequestParam double nuevaTarifaExtra,@RequestParam LocalDate fechaInicio);

}
