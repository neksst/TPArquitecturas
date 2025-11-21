package com.TPE.msFactura.service;

import com.TPE.msFactura.dto.UsoMonopatinUsuarioDTO;
import com.TPE.msFactura.model.DetalleFactura;

import java.time.LocalDate;
import java.util.List;

public interface IDetalleFacturaService {
    List<DetalleFactura> findAll();
    DetalleFactura findById(Long id);
    DetalleFactura create(DetalleFactura detalleFactura);
    DetalleFactura update(DetalleFactura detalleFactura);
    void delete(Long id);
    public List<UsoMonopatinUsuarioDTO> obtenerUsuariosQueMasUsan(LocalDate fechaInicio, LocalDate fechaFin);
}
