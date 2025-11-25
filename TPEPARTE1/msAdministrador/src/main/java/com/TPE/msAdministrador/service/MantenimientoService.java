package com.TPE.msAdministrador.service;

import com.TPE.msAdministrador.client.MonopatinClient;
import com.TPE.msAdministrador.client.ViajeClient;
import com.TPE.msAdministrador.dto.ReporteKilometrosDTO;
import com.TPE.msAdministrador.dto.ViajeDTO;
import com.TPE.msAdministrador.model.Mantenimiento;
import com.TPE.msAdministrador.repository.IMantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MantenimientoService implements IMantenimientoService {

    @Autowired
    private IMantenimientoRepository mantenimientoRepository;
    @Autowired
    private MonopatinClient monopatinClient;
    @Autowired
    private ViajeClient viajeClient;

    @Transactional(readOnly = true)
    public List<Mantenimiento> findAll() {
        return mantenimientoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mantenimiento findById(String id) {
        return mantenimientoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Mantenimiento create(Mantenimiento mantenimiento) {
        return mantenimientoRepository.save(mantenimiento);
    }

    @Transactional
    public void delete(String id) {
        mantenimientoRepository.deleteById(id);
    }

    @Transactional
    public Mantenimiento update(Mantenimiento mantenimiento) {
        return mantenimientoRepository.save(mantenimiento);
    }

    /****************************************************/

    @Transactional
    public Mantenimiento iniciarMantenimiento(Long monopatinId, String descripcion) {
        // Verificar si el monopatín ya está en mantenimiento
        if (mantenimientoRepository.existsByMonopatinId(monopatinId)) {
            throw new RuntimeException("El monopatín ya está en mantenimiento");
        }

        // Crear el registro de mantenimiento
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setMonopatinId(monopatinId);
        mantenimiento.setFechaInicio(LocalDateTime.now());
        mantenimiento.setDescripcion(descripcion);
        mantenimientoRepository.save(mantenimiento);

        // Marcar el monopatín como no disponible
        monopatinClient.actualizarDisponibilidad(monopatinId, false);
        monopatinClient.actualizarEnMantenimiento(monopatinId, true);

        return mantenimiento;
    }

    @Transactional
    public Mantenimiento finalizarMantenimiento(Long monopatinId) {

        Mantenimiento mantenimiento = mantenimientoRepository.findByMonopatinId(monopatinId);

        // Establecer la fecha de finalización
        mantenimiento.setFechaFin(LocalDateTime.now());
        mantenimientoRepository.save(mantenimiento);

        // Marcar el monopatín como disponible
        monopatinClient.actualizarDisponibilidad(monopatinId, true);
        monopatinClient.actualizarEnMantenimiento(monopatinId, false);

        return mantenimiento;
    }

    public List<ReporteKilometrosDTO> generarReporteUsoMonopatines(boolean incluirPausas) {
        List<ViajeDTO> viajes = viajeClient.getAllViajes();
        return viajes.stream()
                .map(v -> {
                    double tiempoTotal = v.getTiempoUso();

                    if (incluirPausas && v.getPausas() != null) {
                        double tiempoPausas = v.getPausas().stream()
                                .filter(p -> p.getFin() != null) // Ignora pausas en curso
                                .mapToDouble(p -> Duration.between(p.getInicio(), p.getFin()).toMinutes())
                                .sum();
                        tiempoTotal -= tiempoPausas;
                    }

                    return new ReporteKilometrosDTO(
                            v.getMonopatinId(), v.getKilometrosRecorridos(), tiempoTotal);
                })
                .collect(Collectors.toList());
    }
}
