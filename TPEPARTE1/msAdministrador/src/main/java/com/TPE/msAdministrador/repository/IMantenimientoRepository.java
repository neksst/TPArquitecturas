package com.TPE.msAdministrador.repository;

import com.TPE.msAdministrador.model.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMantenimientoRepository extends JpaRepository<Mantenimiento,Long> {

    //@Query("SELECT m FROM Mantenimiento m WHERE m.monopatinId = :monopatinId")
    //boolean findByMonopatin(Long monopatinId);

    boolean existsByMonopatinId(Long monopatinId);

    Mantenimiento findByMonopatinId(Long monopatinId);
}
