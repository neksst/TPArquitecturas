package com.TPE.msUsuario.client;

import com.TPE.msUsuario.dto.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microservicio-monopatin", url = "http://localhost:9001")
public interface MonopatinClient {

    @GetMapping("/api/monopatin/cercanos")
    List<MonopatinDTO> obtenerMonopatinesCercanos(
            @RequestParam @Param("latitud") double latitud,
            @RequestParam @Param("longitud") double longitud,
            @RequestParam @Param("radio") double radio
    );
}
