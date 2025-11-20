package com.TPE.msViaje.service;

import com.TPE.msViaje.model.Pausa;
import com.TPE.msViaje.model.Viaje;
import com.TPE.msViaje.repository.IPausaRepository;
import com.TPE.msViaje.repository.IViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PausaService implements IPausaService {
    @Autowired
    private IPausaRepository pausaRepository;
    @Autowired
    private IViajeRepository viajeRepository;

    public List<Pausa> findAll() {
        return pausaRepository.findAll();
    }

    public Pausa findById(Long id) {
        return pausaRepository.findById(id).orElse(null);
    }

    public Pausa save(Pausa pausa) {

        // 1. Obtengo el ID del viaje que vino en el JSON
        Long id = pausa.getViaje().getIdViaje();

        // 2. Cargo el VIAJE REAL desde la BD
        Viaje viajeReal = viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        // 3. Reemplazo el viaje vacío por el viaje real
        pausa.setViaje(viajeReal);

        // 4. Guardo la pausa
        return pausaRepository.save(pausa);
    }

    public Pausa update(Long id, Pausa pausaDetails) {
        Pausa existingPausa = findById(id);
        if (existingPausa != null) {
            existingPausa.setInicio(pausaDetails.getInicio());
            existingPausa.setFin(pausaDetails.getFin());
            existingPausa.setDuracion(pausaDetails.getDuracion());
            return pausaRepository.save(existingPausa);
        }
        return null;
    }

    public void delete(Long id) {
        pausaRepository.deleteById(id);
    }
}
