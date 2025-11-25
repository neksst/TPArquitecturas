package com.TPE.msAdministrador.service;

import com.TPE.msAdministrador.dto.ReporteKilometrosDTO;
import com.TPE.msAdministrador.model.Mantenimiento;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IMantenimientoService {

    List<Mantenimiento> findAll();
    Mantenimiento findById(String id);
    Mantenimiento create(Mantenimiento mantenimiento);
    void delete(String id);
    Mantenimiento update(Mantenimiento mantenimiento);
    Mantenimiento iniciarMantenimiento(Long monopatinId, String descripcion);
    Mantenimiento finalizarMantenimiento(Long monopatinId);
    List<ReporteKilometrosDTO> generarReporteUsoMonopatines(boolean incluirPausas);

}
