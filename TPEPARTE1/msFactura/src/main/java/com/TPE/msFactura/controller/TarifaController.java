package com.TPE.msFactura.controller;


import com.TPE.msFactura.model.Tarifa;
import com.TPE.msFactura.service.ITarifaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tarifa")
@Tag(name = "Tarifa", description = "Endpoints para la gestión de tarifas")
public class TarifaController {

    @Autowired
    private ITarifaService tarifaService;

    // ============================
    // 1. Obtener todas las tarifas
    // ============================
    @Operation(summary = "Obtener todas las tarifas",
            description = "Devuelve la lista completa de tarifas registradas en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de tarifas generado con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay tarifas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Tarifa>> getAllTarifas() {
        List<Tarifa> tarifas = tarifaService.findAll();
        if (tarifas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tarifas);
    }

    // ============================
    // 2. Obtener tarifa por ID
    // ============================
    @Operation(summary = "Obtener tarifa por ID",
            description = "Devuelve una tarifa específica según su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarifa encontrada"),
            @ApiResponse(responseCode = "400", description = "Tarifa no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> getTarifaById(@PathVariable("id") Long id) {
        Tarifa tarifa = tarifaService.findById(id);
        if (tarifa == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tarifa);
    }

    // ============================
    // 3. Crear nueva tarifa
    // ============================
    @Operation(summary = "Crear tarifa",
            description = "Registra una nueva tarifa en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarifa creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear la tarifa")
    })
    @PostMapping
    public ResponseEntity<Tarifa> createTarifa(@RequestBody Tarifa tarifa) {
        Tarifa tarifaCreated = tarifaService.save(tarifa);
        if (tarifaCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tarifaCreated);
    }

    // ============================
    // 4. Eliminar tarifa por ID
    // ============================
    @Operation(summary = "Eliminar tarifa",
            description = "Elimina una tarifa según su ID.")
    @ApiResponse(responseCode = "204", description = "Tarifa eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarifa(@PathVariable("id") Long id) {
        tarifaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // 5. Actualizar tarifa
    // ============================
    @Operation(summary = "Actualizar tarifa",
            description = "Actualiza los datos de una tarifa existente según su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarifa actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Tarifa no encontrada")
    })
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

    // ============================
    // 6. Ajustar precios
    // ============================
    @Operation(summary = "Ajustar precios de tarifas",
            description = "Permite ajustar la tarifa base y extra a partir de una fecha de inicio indicada.")
    @ApiResponse(responseCode = "200", description = "Precios ajustados correctamente")
    @PostMapping("/ajustar-precios")
    public ResponseEntity<Void> ajustarPrecios(
            @RequestParam double nuevaTarifaBase,
            @RequestParam double nuevaTarifaExtra,
            @RequestParam LocalDate fechaInicio) {
        tarifaService.ajustarPrecios(nuevaTarifaBase, nuevaTarifaExtra, fechaInicio);
        return ResponseEntity.ok().build();
    }
}

