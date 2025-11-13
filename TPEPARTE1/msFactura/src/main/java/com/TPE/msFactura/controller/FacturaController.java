package com.TPE.msFactura.controller;


import com.TPE.msFactura.model.Factura;
import com.TPE.msFactura.service.IFacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/factura")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;


    @GetMapping
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> facturas = facturaService.findAll();
        if (facturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable("id") Long id) {
        Factura factura = facturaService.findById(id);
        if (factura == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(factura);
    }

    @PostMapping
    public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
        Factura facturaCreated = facturaService.create(factura);
        if (facturaCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(facturaCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable("id") Long id) {
        facturaService.delete(id);
        return ResponseEntity.noContent().build();
    }

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

    /*******************************************/

    @GetMapping("/total-facturado")
    public ResponseEntity<Double> obtenerTotalFacturado(
            @RequestParam int anio, @RequestParam int mesInicio, @RequestParam int mesFin) {
        double total = facturaService.obtenerTotalFacturado(anio, mesInicio, mesFin);
        return ResponseEntity.ok(total);
    }
}
