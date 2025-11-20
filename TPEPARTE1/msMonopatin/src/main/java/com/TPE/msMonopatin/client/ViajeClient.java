package com.TPE.msMonopatin.client;

import com.TPE.msMonopatin.dto.MonopatinViajesDTO;
import com.TPE.msMonopatin.model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microservicio-viaje", url = "http://localhost:9002")
public interface ViajeClient {

    @GetMapping("/api/viaje/monopatines-con-mas-viajes")
    List<MonopatinViajesDTO> obtenerMonopatinesConMasViajes(
            @RequestParam("minViajes") int minViajes,
            @RequestParam("anio") int anio);

}
