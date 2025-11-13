package com.TPE.msViaje.service;

import com.TPE.msViaje.model.Pausa;
import com.TPE.msViaje.repository.IPausaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PausaService implements IPausaService {
    @Autowired
    private IPausaRepository pausaRepository;

    public List<Pausa> findAll() {
        return pausaRepository.findAll();
    }

    public Pausa findById(Long id) {
        return pausaRepository.findById(id).orElse(null);
    }

    public Pausa save(Pausa pausa) {
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
