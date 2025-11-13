package com.TPE.msMonopatin.service;

import com.TPE.msMonopatin.model.Parada;

import java.util.List;

public interface IParadaService {
    List<Parada> findAll();
    Parada findById(Long id);
    Parada save(Parada parada);
    void delete(Long id);
    Parada update(Parada parada);
}
