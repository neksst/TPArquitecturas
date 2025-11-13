package com.TPE.msFactura.service;

import com.TPE.msFactura.model.DetalleFactura;

import java.util.List;

public interface IDetalleFacturaService {
    List<DetalleFactura> findAll();
    DetalleFactura findById(Long id);
    DetalleFactura create(DetalleFactura detalleFactura);
    DetalleFactura update(DetalleFactura detalleFactura);
    void delete(Long id);
}
