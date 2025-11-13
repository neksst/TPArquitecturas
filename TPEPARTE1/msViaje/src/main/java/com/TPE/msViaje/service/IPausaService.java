package com.TPE.msViaje.service;

import com.TPE.msViaje.model.Pausa;

import java.util.List;

public interface IPausaService {
    List<Pausa> findAll();
    Pausa findById(Long id);
    Pausa save(Pausa pausa);
    Pausa update(Long id, Pausa pausaDetails);
    void delete(Long id);
}
