package edu.tudai.microservicioadministrador.service;


import edu.tudai.microservicioadministrador.client.MonopatinClient;
import edu.tudai.microservicioadministrador.client.cuentaClient;
import edu.tudai.microservicioadministrador.client.facturaClient;
import edu.tudai.microservicioadministrador.client.tarifaClient;
import edu.tudai.microservicioadministrador.client.ViajeClient;
import edu.tudai.microservicioadministrador.dto.ReporteKilometrosDTO;
import edu.tudai.microservicioadministrador.dto.ViajeDTO;
import edu.tudai.microservicioadministrador.entity.Monopatin;
import edu.tudai.microservicioadministrador.entity.Tarifa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class adminService {

    private final cuentaClient cuentaClient;

    private final MonopatinClient monopatinClient;

    private final facturaClient facturaClient;

    private final tarifaClient tarifaClient;

    private final ViajeClient viajeClient;

    public List<Monopatin> obtenerMonopatinesConMasViajes(int minViajes, int anio) {
        return cuentaClient.obtenerMonopatinesConMasViajes(minViajes, anio);
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

    /*----------------------------------------------------------------------------------*/

    // anular cuenta de usuario

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
