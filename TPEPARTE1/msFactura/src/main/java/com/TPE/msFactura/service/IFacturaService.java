package com.TPE.msFactura.service;

import com.TPE.msFactura.model.Factura;

import java.time.LocalDate;
import java.util.List;

public interface IFacturaService {
    List<Factura> findAll();
    Factura findById(Long id);
    Factura create(Factura factura);
    Factura update(Factura factura);
    void delete(Long id);
    double obtenerTotalFacturado(int anio, int mesInicio, int mesFin);
    public List<Factura> findByFechaEmisionBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
