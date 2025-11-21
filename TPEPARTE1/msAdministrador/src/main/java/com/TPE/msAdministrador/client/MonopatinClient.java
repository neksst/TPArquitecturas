package com.TPE.msAdministrador.client;

import com.TPE.msAdministrador.dto.MonopatinDTO;
import com.TPE.msAdministrador.dto.MonopatinViajesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "microservicio-monopatin", url = "http://localhost:9001")
public interface MonopatinClient {

    @PutMapping("/api/monopatin/{id}/disponibilidad")
    void actualizarDisponibilidad(@PathVariable("id") Long id, @RequestParam("disponible") boolean disponible);

    @PutMapping("/api/monopatin/{id}/mantenimiento")
    void actualizarEnMantenimiento(@PathVariable("id") Long id, @RequestParam("enMantenimiento") boolean disponible);

    @GetMapping("/api/monopatin/estado")
    Map<String, Long> obtenerEstadoMonopatines();

    @GetMapping("/api/monopatin/viajes")
    List<MonopatinViajesDTO> obtenerMonopatinesConMasViajes(@RequestParam int minViajes, @RequestParam int anio);
}
