package com.TPE.msAdministrador.controller;

import com.TPE.msAdministrador.dto.MonopatinDTO;
import com.TPE.msAdministrador.dto.MonopatinViajesDTO;
import com.TPE.msAdministrador.dto.ReporteKilometrosDTO;
import com.TPE.msAdministrador.dto.UsoMonopatinUsuarioDTO;
import com.TPE.msAdministrador.service.IAdminService;
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
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Administración", description = "Endpoints administrativos del sistema de monopatines")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    // ============================
    // 1. Anular Cuenta
    // ============================
    @Operation(
            summary = "Anular una cuenta",
            description = "Anula una cuenta de usuario según el ID ingresado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cuenta anulada correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró el usuario")
    })
    @PutMapping("/anularCuenta/{id}")
    public ResponseEntity<Void> anularCuenta(@PathVariable("id") Long id) {
        adminService.anularCuenta(id);
        return ResponseEntity.ok().build();
    }

    // ============================
    // 2. Monopatines con más viajes
    // ============================
    @Operation(
            summary = "Obtener monopatines con más viajes",
            description = "Devuelve los monopatines que superan la cantidad mínima de viajes para un año específico."
    )
    @ApiResponse(responseCode = "200", description = "Listado generado con éxito")
    @GetMapping("/monopatinViajes")
    public ResponseEntity<List<MonopatinViajesDTO>> obtenerMonopatinesConMasViajes(
            @RequestParam int minViajes,
            @RequestParam int anio) {

        List<MonopatinViajesDTO> result =
                adminService.obtenerMonopatinesConMasViajes(minViajes, anio);

        return ResponseEntity.ok(result);
    }

    // ============================
    // 3. Comparación de monopatines
    // ============================
    @Operation(
            summary = "Comparación de monopatines",
            description = "Devuelve estadísticas comparativas de monopatines."
    )
    @ApiResponse(responseCode = "200", description = "Comparación generada correctamente")
    @GetMapping("/monopatines/comparacion")
    public ResponseEntity<Map<String, Long>> monopatinesVS() {
        Map<String, Long> result = adminService.getVSMonopatin();
        return ResponseEntity.ok(result);
    }

    // ============================
    // 4. Total facturado
    // ============================
    @Operation(
            summary = "Total facturado",
            description = "Devuelve el total facturado entre los meses enviados de un año."
    )
    @ApiResponse(responseCode = "200", description = "Cálculo generado con éxito")
    @GetMapping("/factura/total-facturado")
    public ResponseEntity<Double> obtenerTotalFacturado(
            @RequestParam int anio,
            @RequestParam int mesInicio,
            @RequestParam int mesFin) {

        double total = adminService.obtenerTotalFacturado(anio, mesInicio, mesFin);
        return ResponseEntity.ok(total);
    }

    // ============================
    // 5. Ajustar precios de tarifas
    // ============================
    @Operation(
            summary = "Ajustar precios",
            description = "Ajusta las tarifas del sistema a partir de una fecha específica."
    )
    @ApiResponse(responseCode = "200", description = "Tarifas ajustadas correctamente")
    @PostMapping("/tarifa/ajustar-precios")
    public ResponseEntity<Void> ajustarPrecios(
            @RequestParam double nuevaTarifaBase,
            @RequestParam double nuevaTarifaExtra,
            @RequestParam LocalDate fechaInicio) {

        adminService.ajustarPrecios(nuevaTarifaBase, nuevaTarifaExtra, fechaInicio);
        return ResponseEntity.ok().build();
    }

    // ============================
    // 6. Reporte de uso
    // ============================
    @Operation(
            summary = "Generar reporte de uso",
            description = "Devuelve un reporte completo del uso de monopatines."
    )
    @ApiResponse(responseCode = "200", description = "Reporte generado con éxito")
    @GetMapping("/reporte-uso")
    public ResponseEntity<List<ReporteKilometrosDTO>> generarReporteUsoMonopatines(
            @RequestParam boolean incluirPausas) {

        return ResponseEntity.ok(adminService.generarReporteUsoMonopatines(incluirPausas));
    }

    // ============================
    // 7. Usuarios que más usan monopatines
    // ============================
    @Operation(
            summary = "Obtener usuarios que más usan monopatines",
            description = "Devuelve una lista de usuarios que más usaron monopatines entre dos fechas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos en el rango indicado")
    })
    @GetMapping("/obtenerUsuariosQueMasUsanMonopatines")
    public ResponseEntity<List<UsoMonopatinUsuarioDTO>> obtenerUsuariosQueMasUsanMonopatin(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {

        List<UsoMonopatinUsuarioDTO> listUso =
                adminService.obtenerUsuariosQueMasUsanMonopatin(fechaInicio, fechaFin);

        if (listUso == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listUso);
    }
}

