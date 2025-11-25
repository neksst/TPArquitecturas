package com.TPE.msFactura.controller;


import com.TPE.msFactura.model.Factura;
import com.TPE.msFactura.service.IFacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/factura")
@Tag(name = "Factura", description = "Endpoints para la gestión de facturas")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;

    // ============================
    // 1. Obtener todas las facturas
    // ============================
    @Operation(summary = "Obtener todas las facturas",
            description = "Devuelve la lista completa de facturas registradas en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de facturas generado con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay facturas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> facturas = facturaService.findAll();
        if (facturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturas);
    }

    // ============================
    // 2. Obtener factura por ID
    // ============================
    @Operation(summary = "Obtener factura por ID",
            description = "Devuelve una factura específica según su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Factura encontrada"),
            @ApiResponse(responseCode = "400", description = "Factura no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable("id") Long id) {
        Factura factura = facturaService.findById(id);
        if (factura == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(factura);
    }

    // ============================
    // 3. Crear nueva factura
    // ============================
    @Operation(summary = "Crear factura",
            description = "Registra una nueva factura en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Factura creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear la factura")
    })
    @PostMapping
    public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
        Factura facturaCreated = facturaService.create(factura);
        if (facturaCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(facturaCreated);
    }

    // ============================
    // 4. Eliminar factura por ID
    // ============================
    @Operation(summary = "Eliminar factura",
            description = "Elimina una factura según su ID.")
    @ApiResponse(responseCode = "204", description = "Factura eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable("id") Long id) {
        facturaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // 5. Actualizar factura
    // ============================
    @Operation(summary = "Actualizar factura",
            description = "Actualiza los datos de una factura existente según su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Factura actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable("id") Long id, @RequestBody Factura factura) {
        Factura facturaExistente = facturaService.findById(id);
        if (facturaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        facturaExistente.setUsuarioId(factura.getUsuarioId());
        facturaExistente.setFechaEmision(factura.getFechaEmision());

        Factura facturaUpdated = facturaService.create(facturaExistente);
        return ResponseEntity.ok(facturaUpdated);
    }

    // ============================
    // 6. Obtener total facturado
    // ============================
    @Operation(summary = "Obtener total facturado",
            description = "Calcula el total facturado en un rango de meses y año indicados.")
    @ApiResponse(responseCode = "200", description = "Total facturado calculado correctamente")
    @GetMapping("/total-facturado")
    public ResponseEntity<Double> obtenerTotalFacturado(
            @RequestParam int anio,
            @RequestParam int mesInicio,
            @RequestParam int mesFin) {
        double total = facturaService.obtenerTotalFacturado(anio, mesInicio, mesFin);
        return ResponseEntity.ok(total);
    }
}
