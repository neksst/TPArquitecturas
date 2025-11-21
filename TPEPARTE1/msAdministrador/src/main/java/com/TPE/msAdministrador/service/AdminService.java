package com.TPE.msAdministrador.service;

import com.TPE.msAdministrador.client.*;
import com.TPE.msAdministrador.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private CuentaClient cuentaClient;
    @Autowired
    private MonopatinClient monopatinClient;
    @Autowired
    private FacturaClient facturaClient;
    @Autowired
    private TarifaClient tarifaClient;
    @Autowired
    private DetalleFacturaClient detalleFacturaClient;
    @Autowired
    private ViajeClient viajeClient;

    public List<MonopatinViajesDTO> obtenerMonopatinesConMasViajes(int minViajes, int anio) {
        return monopatinClient.obtenerMonopatinesConMasViajes(minViajes, anio);
    }

    public Map<String, Long> getVSMonopatin(){
        return monopatinClient.obtenerEstadoMonopatines();
    }

    public double obtenerTotalFacturado(int anio, int mesInicio, int mesFin) {
        return facturaClient.obtenerTotalFacturado(anio, mesInicio, mesFin);
    }

    public void ajustarPrecios(double nuevaTarifaBase, double nuevaTarifaExtra, LocalDate fechaInicio) {
        tarifaClient.ajustarPrecios(nuevaTarifaBase, nuevaTarifaExtra, fechaInicio);
    }

    public void anularCuenta(Long id) {
        cuentaClient.anularCuenta(id);
    }

    @Override
    public List<UsoMonopatinUsuarioDTO> obtenerUsuariosQueMasUsanMonopatin(LocalDate fechaInicio, LocalDate fechaFin) {
        return detalleFacturaClient.obtenerUsuariosQueMasUsan(fechaInicio,fechaFin);
    }

    /*----------------------------------------------------------------------------------*/


    public List<ReporteKilometrosDTO> generarReporteUsoMonopatines(boolean incluirPausas) {
        List<ViajeDTO> viajes = viajeClient.getAllViajes();
        return viajes.stream()
                .map(v -> {
                    double tiempoTotal = v.getTiempoUso();

                    if (!incluirPausas && v.getPausas() != null) {
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
