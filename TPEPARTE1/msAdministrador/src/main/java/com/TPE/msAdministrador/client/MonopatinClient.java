package com.TPE.msAdministrador.client;

import com.TPE.msAdministrador.dto.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "microservicio-monopatin", url = "http://localhost:9001")
@RequestMapping("/api/monopatin")
public interface MonopatinClient {

    @PutMapping("/{id}/disponibilidad")
    void actualizarDisponibilidad(@PathVariable("id") Long id, @RequestParam("disponible") boolean disponible);

    @PutMapping("/{id}/mantenimiento")
    void actualizarEnMantenimiento(@PathVariable("id") Long id, @RequestParam("enMantenimiento") boolean disponible);

    @GetMapping("/estado")
    Map<String, Long> obtenerEstadoMonopatines();

    @GetMapping("/viajes/{minViajes}/{anio}")
    List<MonopatinDTO> obtenerMonopatinesConMasViajes(@PathVariable("minViajes") int minViajes, @PathVariable("anio") int anio);
}
