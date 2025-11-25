package com.TPE.msUsuario.controller;

import com.TPE.msUsuario.model.Cuenta;
import com.TPE.msUsuario.service.CuentaService;
import com.TPE.msUsuario.service.ICuentaService;
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
@RequestMapping("/api/cuenta")
@Tag(name = "Cuenta", description = "Endpoints para la gestión de cuentas de usuario")
public class CuentaController {

    @Autowired
    private ICuentaService cuentaService;

    // ============================
    // 1. Obtener todas las cuentas
    // ============================
    @Operation(
            summary = "Obtener todas las cuentas",
            description = "Devuelve la lista completa de cuentas registradas en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay cuentas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Cuenta>> getAllCuentas() {
        List<Cuenta> cuentas = cuentaService.findAll();
        if (cuentas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuentas);
    }

    // ============================
    // 2. Obtener cuenta por ID
    // ============================
    @Operation(
            summary = "Obtener cuenta por ID",
            description = "Devuelve los datos de una cuenta específica según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cuenta encontrada"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable("id") Long id) {
        Cuenta cuenta = cuentaService.findById(id);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuenta);
    }

    // ============================
    // 3. Crear cuenta
    // ============================
    @Operation(
            summary = "Crear cuenta",
            description = "Registra una nueva cuenta en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cuenta creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear la cuenta")
    })
    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
        Cuenta cuentaCreated = cuentaService.create(cuenta);
        if (cuentaCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cuentaCreated);
    }

    // ============================
    // 4. Eliminar cuenta
    // ============================
    @Operation(
            summary = "Eliminar cuenta",
            description = "Elimina una cuenta según su ID."
    )
    @ApiResponse(responseCode = "204", description = "Cuenta eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
        cuentaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // 5. Actualizar cuenta
    // ============================
    @Operation(
            summary = "Actualizar cuenta",
            description = "Actualiza los datos de una cuenta existente según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cuenta actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable("id") Long id, @RequestBody Cuenta cuenta) {
        Cuenta cuentaExistente = cuentaService.findById(id);
        if (cuentaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        cuentaExistente.setFechaAlta(cuenta.getFechaAlta());
        cuentaExistente.setSaldo(cuenta.getSaldo());

        Cuenta cuentaUpdated = cuentaService.update(cuentaExistente);
        return ResponseEntity.ok(cuentaUpdated);
    }

    // ============================
    // 6. Anular cuenta
    // ============================
    @Operation(
            summary = "Anular cuenta",
            description = "Anula una cuenta estableciendo su estado como inactiva."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cuenta anulada correctamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PutMapping("/anular/{id}")
    public ResponseEntity<Cuenta> anularCuenta(@PathVariable("id") long id) {
        Cuenta cuenta = cuentaService.findById(id);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }

        cuenta.setActiva(false);
        Cuenta cuentaUpdated = cuentaService.anularCuenta(cuenta);

        return ResponseEntity.ok(cuentaUpdated);
    }
}
