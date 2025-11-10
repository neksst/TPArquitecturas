package com.TPE.msParada.service;

import com.TPE.msParada.model.Parada;

import java.util.List;

public interface IParadaService {

    void addParada(Parada parada);

    void updateParada(Parada parada);

    void deleteParada(Long idParada);

    List<Parada> getParadas();

    Parada getParada(Long idParada);

    void addMonopatin(Long idParada,Long idMonopatin);




}
