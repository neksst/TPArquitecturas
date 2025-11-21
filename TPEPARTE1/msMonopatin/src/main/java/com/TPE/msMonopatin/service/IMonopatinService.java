package com.TPE.msMonopatin.service;

import com.TPE.msMonopatin.dto.MonopatinViajesDTO;
import com.TPE.msMonopatin.model.Monopatin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IMonopatinService {
    List<Monopatin> findAll();
    Monopatin findById(Long id);
    void deleteById(Long id);
    Monopatin create(Monopatin monopatin);
    Monopatin update(Monopatin monopatin);
    Map<String, Long> obtenerEstadoMonopatines();
    List<Monopatin> obtenerMonopatinesCercanos(double latitud, double longitud, double radio);
    List<MonopatinViajesDTO> obtenerMonopatinesConMasViajes(int minViajes, int anio);
    List<Monopatin> obtenerMonopatinesKilometros(Double km);
    void actualizarDisponibilidad(Long id,boolean disponible);
    void actualizarEnMantenimiento(Long id,boolean disponible);
}
