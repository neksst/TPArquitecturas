package com.TPE.msParada.service;

import com.TPE.msParada.model.Parada;
import com.TPE.msParada.repository.IParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService implements IParadaService {
    @Autowired
    IParadaRepository iParadaRepository;

    @Override
    public void addParada(Parada parada) {
        iParadaRepository.save(parada);
    }

    @Override
    public void updateParada(Parada parada) {
        this.addParada(parada);
    }

    @Override
    public void deleteParada(Long idParada) {
        this.iParadaRepository.deleteById(idParada);
    }

    @Override
    public List<Parada> getParadas() {
        return this.iParadaRepository.findAll();
    }

    @Override
    public Parada getParada(Long idParada) {
        return this.iParadaRepository.findById(idParada).get();
    }

    @Override
    public void addMonopatin(Long idParada, Long idMonopatin) {

        //hago conexion con la db del microservicio monopatines me traigo el monopatin/veo si existe

        //me traigo de la db la parada donde quiero add el monopatin

        //if()si existe el monopatin y no esta ya add en la parada esa lo add


    }
}
