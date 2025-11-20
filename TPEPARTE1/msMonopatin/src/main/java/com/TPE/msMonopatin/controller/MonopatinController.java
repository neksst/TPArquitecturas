package com.TPE.msMonopatin.controller;

import com.TPE.msMonopatin.dto.MonopatinViajesDTO;
import com.TPE.msMonopatin.model.Monopatin;
import com.TPE.msMonopatin.service.IMonopatinService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/monopatin")
public class MonopatinController {

    @Autowired
    private IMonopatinService monopatinService;


    @GetMapping
    public ResponseEntity<List<Monopatin>> getAllMonopatines() {
        List<Monopatin> monopatines = monopatinService.findAll();
        if (monopatines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(monopatines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monopatin> getMonopatinById(@PathVariable("id") Long id) {
        Monopatin monopatin = monopatinService.findById(id);
        if (monopatin == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(monopatin);
    }

    @PostMapping
    public ResponseEntity<Monopatin> createMonopatin(@RequestBody Monopatin monopatin) {
        Monopatin monopatinCreated = monopatinService.create(monopatin);
        if (monopatinCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(monopatinCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonopatin(@PathVariable("id") Long id) {
        monopatinService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monopatin> updateMonopatin(@PathVariable("id") Long id, @RequestBody Monopatin monopatin) {
        Monopatin monopatinExistente = monopatinService.findById(id);

        if (monopatinExistente == null) {
            return ResponseEntity.notFound().build();
        }

        monopatinExistente.setBateria(monopatin.getBateria());
        monopatinExistente.setLatitud(monopatin.getLatitud());
        monopatinExistente.setLongitud(monopatin.getLongitud());
        monopatinExistente.setTiempoUso(monopatin.getTiempoUso());
        monopatinExistente.setKilometrosRecorridos(monopatin.getKilometrosRecorridos());
        monopatinExistente.setDisponible(monopatin.isDisponible());
        monopatinExistente.setParada(monopatin.getParada());

        Monopatin monopatinUpdated = monopatinService.update(monopatinExistente);

        return ResponseEntity.ok(monopatinUpdated);
    }

    /********************************************************************/


    @GetMapping("/viajes")
    public ResponseEntity<List<MonopatinViajesDTO>> obtenerMonopatinesConMasViajes(
            @RequestParam int minViajes, @RequestParam int anio) {
        List<MonopatinViajesDTO> result = monopatinService.obtenerMonopatinesConMasViajes(minViajes, anio);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/estado")
    public ResponseEntity<Map<String, Long>> obtenerEstadoMonopatines() {
        Map<String, Long> estado = monopatinService.obtenerEstadoMonopatines();
        return ResponseEntity.ok(estado);
    }

    @GetMapping("/cercanos")
    public ResponseEntity<List<Monopatin>> obtenerMonopatinesCercanos(
            @RequestParam double latitud, @RequestParam double longitud, @RequestParam double radio) {
        List<Monopatin> monopatines = monopatinService.obtenerMonopatinesCercanos(latitud, longitud, radio);
        return ResponseEntity.ok(monopatines);
    }

    @GetMapping("/reporte/kilometros/{km}")
    public ResponseEntity<List<Monopatin>> getReporteKm(@PathVariable("km") Double km){
        List<Monopatin> monopatines = monopatinService.obtenerMonopatinesKilometros(km);
        return ResponseEntity.ok(monopatines);
    }


}
