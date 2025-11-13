package com.TPE.msViaje.repository;

import com.TPE.msViaje.model.Pausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPausaRepository extends JpaRepository<Pausa, Long> {
}
