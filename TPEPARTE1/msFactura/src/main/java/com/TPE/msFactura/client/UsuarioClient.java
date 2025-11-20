package com.TPE.msFactura.client;

import com.TPE.msFactura.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-usuario", url = "http://localhost:9003")
public interface UsuarioClient {

    @GetMapping("/api/usuario/{id}")
    UsuarioDTO getUsuarioById(@PathVariable("id") Long id);
}
