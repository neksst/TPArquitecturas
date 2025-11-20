package com.TPE.msMonopatin.service;

import com.TPE.msMonopatin.client.ViajeClient;
import com.TPE.msMonopatin.dto.MonopatinViajesDTO;
import com.TPE.msMonopatin.model.Monopatin;
import com.TPE.msMonopatin.repository.IMonopatinRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MonopatinService implements IMonopatinService {
    @Autowired
     private IMonopatinRepository monopatinRepository;

    @Autowired
    private ViajeClient viajeClient;

    @Transactional
    public List<Monopatin> findAll() {
        return monopatinRepository.findAll();
    }

    @Transactional
    public Monopatin findById(Long id) {
        return monopatinRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        monopatinRepository.deleteById(id);
    }

    @Transactional
    public Monopatin create(Monopatin monopatin) {
       return  monopatinRepository.save(monopatin);
    }

    @Transactional
    public Monopatin update(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    @Transactional
    public void delete(Long id) {
        monopatinRepository.deleteById(id);
    }

    /*******************************************************/

    public Map<String, Long> obtenerEstadoMonopatines() {
        long enOperacion = monopatinRepository.countByDisponibleTrueAndEnMantenimientoFalse();
        long enMantenimiento = monopatinRepository.countByEnMantenimientoTrue();

        return Map.of("En Operación", enOperacion, "En Mantenimiento", enMantenimiento);
    }

    public List<Monopatin> obtenerMonopatinesCercanos(double latitud, double longitud, double radio) {
        return monopatinRepository.findMonopatinesCercanos(latitud, longitud, radio);
    }

    public List<MonopatinViajesDTO> obtenerMonopatinesConMasViajes(int minViajes, int anio) {
        return viajeClient.obtenerMonopatinesConMasViajes(minViajes, anio);
    }

    public List<Monopatin> obtenerMonopatinesKilometros(Double km) {
        return monopatinRepository.findMonopatinesKilometros(km);
    }
}
