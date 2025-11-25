package com.TPE.msMonopatin.controller;

import com.TPE.msMonopatin.model.Parada;
import com.TPE.msMonopatin.service.IParadaService;
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
@RequestMapping("/api/parada")
@Tag(name = "Parada", description = "Endpoints para la gestión de paradas de monopatines")
public class ParadaController {

    @Autowired
    private IParadaService paradaService;

    // ============================
    // 1. Obtener todas las paradas
    // ============================
    @Operation(
            summary = "Obtener todas las paradas",
            description = "Devuelve la lista completa de paradas registradas en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay paradas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Parada>> getAllParadas(){
        List<Parada> paradas = paradaService.findAll();
        if(paradas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paradas);
    }

    // ============================
    // 2. Obtener parada por ID
    // ============================
    @Operation(
            summary = "Obtener parada por ID",
            description = "Devuelve los datos de una parada específica según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Parada encontrada"),
            @ApiResponse(responseCode = "204", description = "Parada no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Parada> getParadaById(@PathVariable("id") Long id){
        Parada parada = paradaService.findById(id);
        if(parada == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(parada);
    }

    // ============================
    // 3. Crear parada
    // ============================
    @Operation(
            summary = "Crear parada",
            description = "Registra una nueva parada en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Parada creada correctamente"),
            @ApiResponse(responseCode = "204", description = "Error al crear la parada")
    })
    @PostMapping
    public ResponseEntity<Parada> createParada(@RequestBody Parada parada){
        Parada paradaCreated = paradaService.save(parada);
        if(paradaCreated == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paradaCreated);
    }

    // ============================
    // 4. Eliminar parada
    // ============================
    @Operation(
            summary = "Eliminar parada",
            description = "Elimina una parada según su ID."
    )
    @ApiResponse(responseCode = "204", description = "Parada eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Parada> deleteParada(@PathVariable("id") Long id){
        paradaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // 5. Actualizar parada
    // ============================
    @Operation(
            summary = "Actualizar parada",
            description = "Actualiza los datos de una parada existente según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Parada actualizada correctamente"),
            @ApiResponse(responseCode = "204", description = "Parada no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Parada> updateParada(@PathVariable("id") Long id, @RequestBody Parada parada){
        Parada paradaExistente = paradaService.findById(id);
        if(paradaExistente == null){
            return ResponseEntity.noContent().build();
        }

        paradaExistente.setNombre(parada.getNombre());
        paradaExistente.setLatitud(parada.getLatitud());
        paradaExistente.setLongitud(parada.getLongitud());

        Parada paradaUpdated = paradaService.save(paradaExistente);

        return ResponseEntity.ok(paradaUpdated);
    }
}
