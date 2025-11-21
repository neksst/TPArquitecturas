package com.TPE.msAdministrador.controller;

import com.TPE.msAdministrador.dto.MonopatinDTO;
import com.TPE.msAdministrador.dto.MonopatinViajesDTO;
import com.TPE.msAdministrador.dto.ReporteKilometrosDTO;
import com.TPE.msAdministrador.dto.UsoMonopatinUsuarioDTO;
import com.TPE.msAdministrador.service.IAdminService;
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
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PutMapping("/anularCuenta/{id}")
    public ResponseEntity<Void> anularCuenta(@PathVariable("id") Long id) {
        adminService.anularCuenta(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/monopatinViajes")
    public ResponseEntity<List<MonopatinViajesDTO>> obtenerMonopatinesConMasViajes(
            @RequestParam int minViajes, @RequestParam int anio) {
        List<MonopatinViajesDTO> result = adminService.obtenerMonopatinesConMasViajes(minViajes, anio);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/monopatines/comparacion")
    public ResponseEntity<Map<String, Long>> monopatinesVS(){
        Map<String, Long>  result = adminService.getVSMonopatin();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/factura/total-facturado")
    public ResponseEntity<Double> obtenerTotalFacturado(
            @RequestParam int anio, @RequestParam int mesInicio, @RequestParam int mesFin) {
        double total = adminService.obtenerTotalFacturado(anio, mesInicio, mesFin);
        return ResponseEntity.ok(total);
    }

    @PostMapping("/tarifa/ajustar-precios")
    public ResponseEntity<Void> ajustarPrecios(
            @RequestParam double nuevaTarifaBase,
            @RequestParam double nuevaTarifaExtra,
            @RequestParam LocalDate fechaInicio) {
        adminService.ajustarPrecios(nuevaTarifaBase, nuevaTarifaExtra, fechaInicio);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reporte-uso")
    public ResponseEntity<List<ReporteKilometrosDTO>> generarReporteUsoMonopatines(
            @RequestParam boolean incluirPausas) {
        return ResponseEntity.ok(adminService.generarReporteUsoMonopatines(incluirPausas));
    }

    @GetMapping("/obtenerUsuariosQueMasUsanMonopatines")
    public ResponseEntity<List<UsoMonopatinUsuarioDTO>> obtenerUsuariosQueMasUsanMonopatin(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {
        List<UsoMonopatinUsuarioDTO> listUso = adminService.obtenerUsuariosQueMasUsanMonopatin(fechaInicio, fechaFin);

        if (listUso == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listUso);

    }

}
