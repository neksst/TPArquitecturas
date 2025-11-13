package com.TPE.msMonopatin.service;

import com.TPE.msMonopatin.model.Parada;
import com.TPE.msMonopatin.repository.IParadaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService implements IParadaService{

    @Autowired
    private IParadaRepository paradaRepository;

    @Transactional
    public List<Parada> findAll() {
        return paradaRepository.findAll();
    }

    @Transactional
    public Parada findById(Long id) {
        return paradaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Parada save(Parada parada) {
        return paradaRepository.save(parada);
    }

    @Transactional
    public void delete(Long id) {
        paradaRepository.deleteById(id);
    }

    @Transactional
    public Parada update(Parada parada) {
        return paradaRepository.save(parada);
    }

}
