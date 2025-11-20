package com.TPE.msViaje.repository;

import com.TPE.msViaje.dto.MonopatinViajesDTO;
import com.TPE.msViaje.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT new com.TPE.msViaje.dto.MonopatinViajesDTO(v.monopatinId, COUNT(v)) FROM Viaje v where YEAR(v.fechaFin) < :anio GROUP BY v.monopatinId HAVING COUNT(v) > :minViajes")
    List<MonopatinViajesDTO> obtenerMonopatinesConMasViajes(@Param("minViajes") int minViajes, @Param("anio") int anio);

}
