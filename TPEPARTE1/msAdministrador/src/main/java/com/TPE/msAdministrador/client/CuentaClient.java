package com.TPE.msAdministrador.client;

import com.TPE.msAdministrador.dto.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "microservicio-usuario", url = "http://localhost:9003")
@RequestMapping("/api/cuenta")
public interface CuentaClient {

    @PutMapping("/anularCuenta/{id}")
    void anularCuenta(@PathVariable("id") long id);

}
