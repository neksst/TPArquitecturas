package com.TPE.msFactura.controller;

import com.TPE.msFactura.dto.UsoMonopatinUsuarioDTO;
import com.TPE.msFactura.model.DetalleFactura;
import com.TPE.msFactura.service.IDetalleFacturaService;
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
@RequestMapping("/api/detallefactura")
@Tag(name = "DetalleFactura", description = "Endpoints para la gestión de detalles de factura")
public class DetalleFacturaController {

    @Autowired
    private IDetalleFacturaService detalleFacturaService;

    // ============================
    // 1. Obtener todos los detalles de factura
    // ============================
    @Operation(
            summary = "Obtener todos los detalles de factura",
            description = "Devuelve la lista completa de detalles de factura registrados en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay detalles de factura registrados")
    })
    @GetMapping
    public ResponseEntity<List<DetalleFactura>> getAllDetallesFactura() {
        List<DetalleFactura> detalleFacturas = detalleFacturaService.findAll();
        if (detalleFacturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detalleFacturas);
    }

    // ============================
    // 2. Obtener detalle de factura por ID
    // ============================
    @Operation(
            summary = "Obtener detalle de factura por ID",
            description = "Devuelve un detalle de factura específico según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalle encontrado"),
            @ApiResponse(responseCode = "400", description = "Detalle no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> getDetalleFacturaById(@PathVariable("id") Long id) {
        DetalleFactura detalleFactura = detalleFacturaService.findById(id);
        if (detalleFactura == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(detalleFactura);
    }

    // ============================
    // 3. Crear detalle de factura
    // ============================
    @Operation(
            summary = "Crear detalle de factura",
            description = "Registra un nuevo detalle de factura en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalle creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el detalle")
    })
    @PostMapping
    public ResponseEntity<DetalleFactura> createDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        DetalleFactura detalleFacturaCreated = detalleFacturaService.create(detalleFactura);
        if (detalleFacturaCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(detalleFacturaCreated);
    }

    // ============================
    // 4. Eliminar detalle de factura
    // ============================
    @Operation(
            summary = "Eliminar detalle de factura",
            description = "Elimina un detalle de factura según su ID."
    )
    @ApiResponse(responseCode = "204", description = "Detalle eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleFactura(@PathVariable("id") Long id) {
        detalleFacturaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // 5. Actualizar detalle de factura
    // ============================
    @Operation(
            summary = "Actualizar detalle de factura",
            description = "Actualiza los datos de un detalle de factura existente según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalle actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> updateDetalleFactura(@PathVariable("id") Long id, @RequestBody DetalleFactura detalleFactura) {
        DetalleFactura detalleFacturaExistente = detalleFacturaService.findById(id);

        if (detalleFacturaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        detalleFacturaExistente.setFactura(detalleFactura.getFactura());
        detalleFacturaExistente.setTiempoUso(detalleFactura.getTiempoUso());
        detalleFacturaExistente.setTiempoPausado(detalleFactura.getTiempoPausado());
        detalleFacturaExistente.setTarifaBase(detalleFactura.getTarifaBase());
        detalleFacturaExistente.setTarifaExtra(detalleFactura.getTarifaExtra());
        detalleFacturaExistente.setViajeId(detalleFactura.getViajeId());

        DetalleFactura detalleFacturaUpdated = detalleFacturaService.update(detalleFacturaExistente);

        return ResponseEntity.ok(detalleFacturaUpdated);
    }

    // ============================
    // 6. Obtener usuarios que más usan monopatines
    // ============================
    @Operation(
            summary = "Usuarios que más usan monopatines",
            description = "Devuelve una lista de usuarios que más usaron monopatines entre dos fechas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos en el rango indicado")
    })
    @GetMapping("/obtenerUsuariosQueMasUsanMonopatines")
    public ResponseEntity<List<UsoMonopatinUsuarioDTO>> obtenerUsuariosQueMasUsan(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {

        List<UsoMonopatinUsuarioDTO> listUso = detalleFacturaService.obtenerUsuariosQueMasUsan(fechaInicio, fechaFin);

        if (listUso == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listUso);
    }
}

