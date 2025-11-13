package com.TPE.msMonopatin.repository;

import com.TPE.msMonopatin.model.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParadaRepository extends JpaRepository<Parada, Long> {

}
