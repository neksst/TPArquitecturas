package com.TPE.msAdministrador.client;

import com.TPE.msAdministrador.dto.UsoMonopatinUsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "microservicio-factura",contextId = "detalleFacturaClient", url = "http://localhost:9004")
public interface DetalleFacturaClient {


    @GetMapping("/api/detallefactura/obtenerUsuariosQueMasUsanMonopatines")
    public List<UsoMonopatinUsuarioDTO> obtenerUsuariosQueMasUsan(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin);


    }
