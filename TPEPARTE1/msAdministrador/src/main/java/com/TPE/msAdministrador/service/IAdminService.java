package com.TPE.msAdministrador.service;

import com.TPE.msAdministrador.dto.MonopatinDTO;
import com.TPE.msAdministrador.dto.ReporteKilometrosDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAdminService {
    List<MonopatinDTO> obtenerMonopatinesConMasViajes(int minViajes, int anio);
    Map<String, Long> getVSMonopatin();
    public double obtenerTotalFacturado(int anio, int mesInicio, int mesFin);
    public void ajustarPrecios(double nuevaTarifaBase, double nuevaTarifaExtra, LocalDate fechaInicio);
    public List<ReporteKilometrosDTO> generarReporteUsoMonopatines(boolean incluirPausas);
    void anularCuenta(Long id);
}
