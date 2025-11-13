package com.TPE.msViaje.service;

import com.TPE.msViaje.dto.ReporteKilometrosDTO;
import com.TPE.msViaje.model.Viaje;

import java.util.List;

public interface IViajeService {
    List<Viaje> findAll();
    Viaje findById(Long id);
    Viaje save(Viaje viaje);
    void delete(Long id);
    Viaje update(Viaje viaje);
    Double calcularTiempoTotalConPausas(Long viajeId);
    Viaje finalizarViaje(Long viajeId, double kilometrosRecorridos);
    List<ReporteKilometrosDTO> generarReporteKilometros();

}
