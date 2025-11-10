package com.TPE.msParada.repository;

import com.TPE.msParada.model.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IParadaRepository extends JpaRepository<Parada, Long> {
}
