package com.TPE.msAdministrador.client;

import com.TPE.msAdministrador.dto.ViajeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "microservicio-viaje", url = "http://localhost:9002")
public interface ViajeClient {

    @GetMapping("/api/viaje")
    List<ViajeDTO> getAllViajes();

}
