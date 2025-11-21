package com.TPE.msAdministrador.service;

import com.TPE.msAdministrador.dto.MonopatinDTO;
import com.TPE.msAdministrador.dto.MonopatinViajesDTO;
import com.TPE.msAdministrador.dto.ReporteKilometrosDTO;
import com.TPE.msAdministrador.dto.UsoMonopatinUsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAdminService {
    List<MonopatinViajesDTO> obtenerMonopatinesConMasViajes(int minViajes, int anio);
    Map<String, Long> getVSMonopatin();
    public double obtenerTotalFacturado(int anio, int mesInicio, int mesFin);
    public void ajustarPrecios(double nuevaTarifaBase, double nuevaTarifaExtra, LocalDate fechaInicio);
    public List<ReporteKilometrosDTO> generarReporteUsoMonopatines(boolean incluirPausas);
    void anularCuenta(Long id);
    List<UsoMonopatinUsuarioDTO> obtenerUsuariosQueMasUsanMonopatin(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin);
}
