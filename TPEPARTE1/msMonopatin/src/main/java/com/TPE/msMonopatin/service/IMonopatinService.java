package com.TPE.msMonopatin.service;

import com.TPE.msMonopatin.model.Monopatin;

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
    List<Monopatin> obtenerMonopatinesConMasViajes(int minViajes, int anio);
    List<Monopatin> obtenerMonopatinesKilometros(Double km);
}
