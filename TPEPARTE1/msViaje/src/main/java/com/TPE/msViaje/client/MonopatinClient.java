package com.TPE.msViaje.client;

import com.TPE.msViaje.dto.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-monopatin", url = "http://localhost:9001")
public interface MonopatinClient {

    @GetMapping("/api/monopatin/{id}")
    public ResponseEntity<MonopatinDTO> getMonopatinById(@PathVariable("id") Long id);

}
