package com.TPE.msUsuario.client;

import com.TPE.msUsuario.dto.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "microservicio-monopatin", url = "http://localhost:9001")
@RequestMapping("/api/monopatin")
public interface MonopatinClient {

    @GetMapping("/cercanos")
    List<MonopatinDTO> obtenerMonopatinesCercanos(
            @Param("latitud") double latitud,
            @Param("longitud") double longitud,
            @Param("radio") double radio
    );
}
