package com.TPE.msAdministrador.controller;


import com.TPE.msAdministrador.dto.ReporteKilometrosDTO;
import com.TPE.msAdministrador.model.Mantenimiento;

import com.TPE.msAdministrador.service.IMantenimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mantenimiento")
@Tag(name = "Mantenimiento", description = "Endpoints para la gestión de mantenimientos de monopatines")
public class MantenimientoController {

    @Autowired
    private IMantenimientoService mantenimientoService;

    // ============================
    // 1. Obtener todos los mantenimientos
    // ============================
    @Operation(
            summary = "Obtener todos los mantenimientos",
            description = "Devuelve la lista completa de mantenimientos registrados en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado generado con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay mantenimientos registrados")
    })
    @GetMapping
    public ResponseEntity<List<Mantenimiento>> getAllMantenimientos() {
        List<Mantenimiento> mantenimientos = mantenimientoService.findAll();
        if (mantenimientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mantenimientos);
    }

    // ============================
    // 2. Obtener mantenimiento por ID
    // ============================
    @Operation(
            summary = "Obtener mantenimiento por ID",
            description = "Devuelve los datos de un mantenimiento específico según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mantenimiento encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido o mantenimiento no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento> getMantenimientoById(@PathVariable("id") String id) {
        Mantenimiento mantenimiento = mantenimientoService.findById(id);
        if (mantenimiento == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(mantenimiento);
    }

    // ============================
    // 3. Crear mantenimiento
    // ============================
    @Operation(
            summary = "Crear mantenimiento",
            description = "Registra un nuevo mantenimiento en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mantenimiento creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el mantenimiento")
    })
    @PostMapping
    public ResponseEntity<Mantenimiento> createMantenimiento(@RequestBody Mantenimiento mantenimiento) {
        Mantenimiento mantenimientoCreated = mantenimientoService.create(mantenimiento);
        if (mantenimientoCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(mantenimientoCreated);
    }

    // ============================
    // 4. Eliminar mantenimiento
    // ============================
    @Operation(
            summary = "Eliminar mantenimiento",
            description = "Elimina un mantenimiento según su ID."
    )
    @ApiResponse(responseCode = "204", description = "Mantenimiento eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMantenimiento(@PathVariable("id") String id) {
        mantenimientoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // 5. Actualizar mantenimiento
    // ============================
    @Operation(
            summary = "Actualizar mantenimiento",
            description = "Actualiza los datos de un mantenimiento existente según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mantenimiento actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Mantenimiento no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Mantenimiento> updatemantenimiento(
            @PathVariable("id") String id,
            @RequestBody Mantenimiento mantenimiento) {

        Mantenimiento mantenimientoExistente = mantenimientoService.findById(id);

        if (mantenimientoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        mantenimientoExistente.setDescripcion(mantenimiento.getDescripcion());
        mantenimientoExistente.setMonopatinId(mantenimiento.getMonopatinId());
        mantenimientoExistente.setFechaInicio(mantenimiento.getFechaInicio());
        mantenimientoExistente.setFechaFin(mantenimiento.getFechaFin());

        Mantenimiento mantenimientoUpdated = mantenimientoService.update(mantenimientoExistente);

        return ResponseEntity.ok(mantenimientoUpdated);
    }

    // ============================
    // 6. Iniciar mantenimiento
    // ============================
    @Operation(
            summary = "Iniciar mantenimiento",
            description = "Inicia un mantenimiento para un monopatín específico con una descripción opcional."
    )
    @ApiResponse(responseCode = "200", description = "Mantenimiento iniciado correctamente")
    @PostMapping("/iniciar/{monopatinId}")
    public ResponseEntity<Mantenimiento> iniciarMantenimiento(
            @PathVariable Long monopatinId,
            @RequestBody(required = false) String descripcion) {

        Mantenimiento mantenimiento = mantenimientoService.iniciarMantenimiento(monopatinId, descripcion);
        return ResponseEntity.ok(mantenimiento);
    }

    // ============================
    // 7. Finalizar mantenimiento
    // ============================
    @Operation(
            summary = "Finalizar mantenimiento",
            description = "Finaliza el mantenimiento de un monopatín según su ID."
    )
    @ApiResponse(responseCode = "200", description = "Mantenimiento finalizado correctamente")
    @PutMapping("/finalizar/{monopatinId}")
    public ResponseEntity<Mantenimiento> finalizarMantenimiento(@PathVariable Long monopatinId) {
        Mantenimiento mantenimiento = mantenimientoService.finalizarMantenimiento(monopatinId);
        return ResponseEntity.ok(mantenimiento);
    }

    // ============================
    // 8. Generar reporte de uso
    // ============================
    @Operation(
            summary = "Generar reporte de uso",
            description = "Devuelve un reporte completo del uso de monopatines durante los mantenimientos."
    )
    @ApiResponse(responseCode = "200", description = "Reporte generado correctamente")
    @GetMapping("/reporte-uso")
    public ResponseEntity<List<ReporteKilometrosDTO>> generarReporteUsoMonopatines(
            @RequestParam boolean incluirPausas) {

        return ResponseEntity.ok(mantenimientoService.generarReporteUsoMonopatines(incluirPausas));
    }

}

