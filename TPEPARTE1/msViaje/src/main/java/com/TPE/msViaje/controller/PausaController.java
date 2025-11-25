package com.TPE.msViaje.controller;

import com.TPE.msViaje.model.Pausa;
import com.TPE.msViaje.service.IPausaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pausa")
@Tag(name = "Pausa", description = "Endpoints para la gestión de pausas de uso de monopatines")
public class PausaController {

    @Autowired
    private IPausaService pausaService;

    // ============================
    // 1. Obtener todas las pausas
    // ============================
    @Operation(
            summary = "Obtener todas las pausas",
            description = "Devuelve la lista completa de pausas registradas en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay pausas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Pausa>> getAllPausas() {
        List<Pausa> pausas = pausaService.findAll();
        if (pausas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pausas);
    }

    // ============================
    // 2. Obtener pausa por ID
    // ============================
    @Operation(
            summary = "Obtener pausa por ID",
            description = "Devuelve los datos de una pausa específica según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pausa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pausa no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pausa> getPausaById(@PathVariable Long id) {
        Pausa pausa = pausaService.findById(id);
        return pausa != null ? ResponseEntity.ok(pausa) : ResponseEntity.notFound().build();
    }

    // ============================
    // 3. Crear pausa
    // ============================
    @Operation(
            summary = "Crear pausa",
            description = "Registra una nueva pausa en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Pausa creada correctamente")
    @PostMapping
    public ResponseEntity<Pausa> createPausa(@RequestBody Pausa pausa) {
        Pausa createdPausa = pausaService.save(pausa);
        return ResponseEntity.ok(createdPausa);
    }

    // ============================
    // 4. Actualizar pausa
    // ============================
    @Operation(
            summary = "Actualizar pausa",
            description = "Actualiza los datos de una pausa existente según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pausa actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Pausa no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pausa> updatePausa(@PathVariable Long id, @RequestBody Pausa pausa) {
        Pausa updatedPausa = pausaService.update(id, pausa);
        return updatedPausa != null ? ResponseEntity.ok(updatedPausa) : ResponseEntity.notFound().build();
    }

    // ============================
    // 5. Eliminar pausa
    // ============================
    @Operation(
            summary = "Eliminar pausa",
            description = "Elimina una pausa según su ID."
    )
    @ApiResponse(responseCode = "204", description = "Pausa eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePausa(@PathVariable Long id) {
        pausaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
