package com.TPE.msViaje.service;

import com.TPE.msViaje.dto.MonopatinViajesDTO;
import com.TPE.msViaje.dto.ReporteKilometrosDTO;
import com.TPE.msViaje.model.Viaje;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IViajeService {
    List<Viaje> findAll();
    Viaje findById(Long id);
    Viaje create(Viaje viaje);
    void delete(Long id);
    Viaje update(Viaje viaje);
    Double calcularTiempoTotalConPausas(Long viajeId);
    Viaje finalizarViaje(Long viajeId, double kilometrosRecorridos);
    List<ReporteKilometrosDTO> generarReporteKilometros();
    List<MonopatinViajesDTO> obtenerMonopatinesConMasViajes(int minViajes, int anio);

}
