package com.TPE.msMonopatin.controller;

import com.TPE.msMonopatin.dto.MonopatinViajesDTO;
import com.TPE.msMonopatin.model.Monopatin;
import com.TPE.msMonopatin.service.IMonopatinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/monopatin")
@Tag(name = "Monopatín", description = "Endpoints para la gestión de monopatines")
public class MonopatinController {

    @Autowired
    private IMonopatinService monopatinService;

    // ============================
    // 1. Obtener todos los monopatines
    // ============================
    @Operation(
            summary = "Obtener todos los monopatines",
            description = "Devuelve la lista completa de monopatines disponibles en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay monopatines registrados")
    })
    @GetMapping
    public ResponseEntity<List<Monopatin>> getAllMonopatines() {
        List<Monopatin> monopatines = monopatinService.findAll();
        if (monopatines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(monopatines);
    }

    // ============================
    // 2. Obtener monopatín por ID
    // ============================
    @Operation(
            summary = "Obtener monopatín por ID",
            description = "Devuelve los datos de un monopatín específico según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Monopatín encontrado"),
            @ApiResponse(responseCode = "400", description = "Monopatín no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Monopatin> getMonopatinById(@PathVariable("id") Long id) {
        Monopatin monopatin = monopatinService.findById(id);
        if (monopatin == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(monopatin);
    }

    // ============================
    // 3. Crear monopatín
    // ============================
    @Operation(
            summary = "Crear monopatín",
            description = "Registra un nuevo monopatín en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Monopatín creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el monopatín")
    })
    @PostMapping
    public ResponseEntity<Monopatin> createMonopatin(@RequestBody Monopatin monopatin) {
        Monopatin monopatinCreated = monopatinService.create(monopatin);
        if (monopatinCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(monopatinCreated);
    }

    // ============================
    // 4. Eliminar monopatín
    // ============================
    @Operation(
            summary = "Eliminar monopatín",
            description = "Elimina un monopatín según su ID."
    )
    @ApiResponse(responseCode = "204", description = "Monopatín eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonopatin(@PathVariable("id") Long id) {
        monopatinService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // 5. Actualizar monopatín
    // ============================
    @Operation(
            summary = "Actualizar monopatín",
            description = "Actualiza los datos de un monopatín existente según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Monopatín actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Monopatín no encontrado")
    })
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

    // ============================
    // 6. Obtener monopatines con más viajes
    // ============================
    @Operation(
            summary = "Monopatines con más viajes",
            description = "Devuelve los monopatines que superan la cantidad mínima de viajes para un año específico."
    )
    @ApiResponse(responseCode = "200", description = "Listado generado con éxito")
    @GetMapping("/viajes")
    public ResponseEntity<List<MonopatinViajesDTO>> obtenerMonopatinesConMasViajes(
            @RequestParam int minViajes,
            @RequestParam int anio) {
        List<MonopatinViajesDTO> result = monopatinService.obtenerMonopatinesConMasViajes(minViajes, anio);
        return ResponseEntity.ok(result);
    }

    // ============================
    // 7. Obtener estado de monopatines
    // ============================
    @Operation(
            summary = "Estado de monopatines",
            description = "Devuelve un conteo de monopatines por estado (disponible, en mantenimiento, etc.)."
    )
    @GetMapping("/estado")
    public ResponseEntity<Map<String, Long>> obtenerEstadoMonopatines() {
        Map<String, Long> estado = monopatinService.obtenerEstadoMonopatines();
        return ResponseEntity.ok(estado);
    }

    // ============================
    // 8. Obtener monopatines cercanos
    // ============================
    @Operation(
            summary = "Monopatines cercanos",
            description = "Devuelve los monopatines dentro de un radio específico alrededor de una ubicación."
    )
    @GetMapping("/cercanos")
    public ResponseEntity<List<Monopatin>> obtenerMonopatinesCercanos(
            @RequestParam double latitud,
            @RequestParam double longitud,
            @RequestParam double radio) {
        List<Monopatin> monopatines = monopatinService.obtenerMonopatinesCercanos(latitud, longitud, radio);
        return ResponseEntity.ok(monopatines);
    }

    // ============================
    // 9. Monopatines por kilómetros
    // ============================
    @Operation(
            summary = "Monopatines por kilómetros",
            description = "Devuelve los monopatines que han recorrido más de una cantidad específica de kilómetros."
    )
    @GetMapping("/reporte/kilometros/{km}")
    public ResponseEntity<List<Monopatin>> getReporteKm(@PathVariable("km") Double km){
        List<Monopatin> monopatines = monopatinService.obtenerMonopatinesKilometros(km);
        return ResponseEntity.ok(monopatines);
    }

    // ============================
    // 10. Actualizar disponibilidad
    // ============================
    @Operation(
            summary = "Actualizar disponibilidad",
            description = "Marca un monopatín como disponible o no disponible."
    )
    @PutMapping("/{id}/disponibilidad")
    public void actualizarDisponibilidad(@PathVariable("id") Long id, @RequestParam("disponible") boolean disponible){
        monopatinService.actualizarDisponibilidad(id, disponible);
    }

    // ============================
    // 11. Actualizar estado de mantenimiento
    // ============================
    @Operation(
            summary = "Actualizar en mantenimiento",
            description = "Marca un monopatín como en mantenimiento o fuera de mantenimiento."
    )
    @PutMapping("/{id}/mantenimiento")
    public void actualizarEnMantenimiento(@PathVariable("id") Long id, @RequestParam("enMantenimiento") boolean disponible){
        monopatinService.actualizarEnMantenimiento(id, disponible);
    }
}

