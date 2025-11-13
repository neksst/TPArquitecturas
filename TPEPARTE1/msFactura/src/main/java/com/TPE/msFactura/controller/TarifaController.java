package com.TPE.msFactura.controller;


import com.TPE.msFactura.model.Tarifa;
import com.TPE.msFactura.service.ITarifaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tarifa")
public class TarifaController {

    @Autowired
    private ITarifaService tarifaService;

    @GetMapping
    public ResponseEntity<List<Tarifa>> getAllTarifas() {
        List<Tarifa> tarifas = tarifaService.findAll();
        if (tarifas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tarifas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> getFacturaById(@PathVariable("id") Long id) {
        Tarifa tarifa = tarifaService.findById(id);
        if (tarifa == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tarifa);
    }

    @PostMapping
    public ResponseEntity<Tarifa> createTarifa(@RequestBody Tarifa tarifa) {
        Tarifa tarifaCreated = tarifaService.save(tarifa);
        if (tarifaCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tarifaCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarifa(@PathVariable("id") Long id) {
        tarifaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarifa> updateTarifa(@PathVariable("id") Long id, @RequestBody Tarifa tarifa) {
        Tarifa tarifaExistente = tarifaService.findById(id);

        if (tarifaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        tarifaExistente.setTipo(tarifa.getTipo());
        tarifaExistente.setFechaInicio(tarifa.getFechaInicio());
        tarifaExistente.setFechaFin(tarifa.getFechaFin());
        tarifaExistente.setMonto(tarifa.getMonto());

        Tarifa tarifaUpdated = tarifaService.save(tarifaExistente);

        return ResponseEntity.ok(tarifaUpdated);
    }

    /****************************************/

    @PostMapping("/ajustar-precios")
    public ResponseEntity<Void> ajustarPrecios(
            @RequestParam double nuevaTarifaBase,
            @RequestParam double nuevaTarifaExtra,
            @RequestParam LocalDate fechaInicio) {
        tarifaService.ajustarPrecios(nuevaTarifaBase, nuevaTarifaExtra, fechaInicio);
        return ResponseEntity.ok().build();
    }
}
