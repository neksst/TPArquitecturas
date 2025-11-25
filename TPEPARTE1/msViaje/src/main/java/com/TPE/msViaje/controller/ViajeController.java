package com.TPE.msViaje.controller;


import com.TPE.msViaje.dto.MonopatinViajesDTO;
import com.TPE.msViaje.dto.ReporteKilometrosDTO;
import com.TPE.msViaje.model.Pausa;
import com.TPE.msViaje.model.Viaje;
import com.TPE.msViaje.service.IViajeService;
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
@RequestMapping("/api/viaje")
@Tag(name = "Viaje", description = "Endpoints para la gestión de viajes de monopatines")
public class ViajeController {

    @Autowired
    private IViajeService viajeService;

    // ============================
    // 1. Obtener todos los viajes
    // ============================
    @Operation(
            summary = "Obtener todos los viajes",
            description = "Devuelve la lista completa de viajes registrados en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay viajes registrados")
    })
    @GetMapping
    public ResponseEntity<List<Viaje>> getAllViajes(){
        List<Viaje> viajes = viajeService.findAll();
        if(viajes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(viajes);
    }

    // ============================
    // 2. Obtener viaje por ID
    // ============================
    @Operation(
            summary = "Obtener viaje por ID",
            description = "Devuelve los datos de un viaje específico según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Viaje encontrado"),
            @ApiResponse(responseCode = "204", description = "Viaje no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Viaje> getViaje(@PathVariable("id") Long id){
        Viaje viaje = viajeService.findById(id);
        if(viaje == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(viaje);
    }

    // ============================
    // 3. Crear viaje
    // ============================
    @Operation(
            summary = "Crear viaje",
            description = "Registra un nuevo viaje en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Viaje creado correctamente"),
            @ApiResponse(responseCode = "204", description = "Error al crear el viaje")
    })
    @PostMapping
    public ResponseEntity<Viaje> createViaje(@RequestBody Viaje viaje){
        Viaje viajeCreated = viajeService.create(viaje);
        if(viajeCreated == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(viajeCreated);
    }

    // ============================
    // 4. Eliminar viaje
    // ============================
    @Operation(
            summary = "Eliminar viaje",
            description = "Elimina un viaje según su ID."
    )
    @ApiResponse(responseCode = "204", description = "Viaje eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Viaje> deleteViaje(@PathVariable("id") Long id){
        viajeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // 5. Actualizar viaje
    // ============================
    @Operation(
            summary = "Actualizar viaje",
            description = "Actualiza los datos de un viaje existente según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Viaje actualizado correctamente"),
            @ApiResponse(responseCode = "204", description = "Viaje no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Viaje> updateViaje(@PathVariable("id") Long id, @RequestBody Viaje viaje){
        Viaje viajeExistente = viajeService.findById(id);
        if(viajeExistente == null){
            return ResponseEntity.noContent().build();
        }

        viajeExistente.setFechaInicio(viaje.getFechaInicio());
        viajeExistente.setFechaFin(viaje.getFechaFin());
        viajeExistente.setMonopatinId(viaje.getMonopatinId());
        viajeExistente.setEnCurso(viaje.isEnCurso());
        viajeExistente.setKilometrosRecorridos(viaje.getKilometrosRecorridos());

        Viaje viajeUpdated = viajeService.create(viajeExistente);
        return ResponseEntity.ok(viajeUpdated);
    }

    // ============================
    // 6. Obtener tiempo total con pausas
    // ============================
    @Operation(
            summary = "Calcular tiempo total con pausas",
            description = "Devuelve el tiempo total de un viaje incluyendo las pausas asociadas."
    )
    @ApiResponse(responseCode = "200", description = "Cálculo realizado correctamente")
    @GetMapping("/{viajeId}/tiempo-total-con-pausas")
    public ResponseEntity<Double> obtenerTiempoTotalConPausas(@PathVariable("viajeId") Long viajeId) {
        Double tiempoTotalConPausas = viajeService.calcularTiempoTotalConPausas(viajeId);
        return ResponseEntity.ok(tiempoTotalConPausas);
    }

    // ============================
    // 7. Finalizar viaje
    // ============================
    @Operation(
            summary = "Finalizar viaje",
            description = "Finaliza un viaje e indica los kilómetros recorridos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Viaje finalizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al finalizar el viaje")
    })
    @PutMapping("/{viajeId}/finalizar")
    public ResponseEntity<Viaje> finalizarViaje(@PathVariable Long viajeId, @RequestParam double kilometrosRecorridos) {
        try {
            Viaje viajeFinalizado = viajeService.finalizarViaje(viajeId, kilometrosRecorridos);
            return ResponseEntity.ok(viajeFinalizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // ============================
    // 8. Agregar pausa a viaje
    // ============================
    @Operation(
            summary = "Agregar pausa a viaje",
            description = "Agrega una pausa al viaje especificado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pausa agregada correctamente"),
            @ApiResponse(responseCode = "204", description = "Viaje no encontrado")
    })
    @PutMapping("/agregarPausa/{id}")
    public ResponseEntity<Viaje> agregarPausa(@PathVariable("id") Long id, @RequestBody Pausa pausa) {
        Viaje viajeExistente = viajeService.findById(id);
        if (viajeExistente == null) {
            return ResponseEntity.noContent().build();
        }

        pausa.setViaje(viajeExistente);
        viajeExistente.getPausas().add(pausa);
        viajeService.create(viajeExistente);
        return ResponseEntity.ok(viajeExistente);
    }

    // ============================
    // 9. Generar reporte de kilómetros
    // ============================
    @Operation(
            summary = "Generar reporte de kilómetros",
            description = "Devuelve un listado con el total de kilómetros recorridos por viaje."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reporte generado correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay datos para generar el reporte")
    })
    @GetMapping("/reportes/kilometros")
    public ResponseEntity<List<ReporteKilometrosDTO>> obtenerReporteKilometros() {
        List<ReporteKilometrosDTO> reporte = viajeService.generarReporteKilometros();
        if (reporte.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reporte);
    }

    // ============================
    // 10. Monopatines con más viajes
    // ============================
    @Operation(
            summary = "Obtener monopatines con más viajes",
            description = "Devuelve los monopatines que superan la cantidad mínima de viajes en un año específico."
    )
    @ApiResponse(responseCode = "200", description = "Listado generado correctamente")
    @GetMapping("/monopatines-con-mas-viajes")
    public List<MonopatinViajesDTO> obtenerMonopatinesConMasViajes(
            @RequestParam("minViajes") int minViajes,
            @RequestParam("anio") int anio){
        return viajeService.obtenerMonopatinesConMasViajes(minViajes,anio);
    }
}
