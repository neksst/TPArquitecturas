package com.TPE.msFactura.service;

import com.TPE.msFactura.model.Tarifa;

import java.time.LocalDate;
import java.util.List;

public interface ITarifaService {
    List<Tarifa> findAll();
    Tarifa findById(Long id);
    Tarifa save(Tarifa tarifa);
    Tarifa update(Tarifa tarifa);
    void delete(Long id);
    void ajustarPrecios(double nuevaTarifaBase, double nuevaTarifaExtra, LocalDate fechaInicio);
}
