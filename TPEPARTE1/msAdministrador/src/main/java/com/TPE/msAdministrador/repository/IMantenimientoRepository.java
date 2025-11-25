package com.TPE.msAdministrador.repository;

import com.TPE.msAdministrador.model.Mantenimiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface IMantenimientoRepository extends MongoRepository<Mantenimiento, String> {

    boolean existsByMonopatinId(Long monopatinId);

    Mantenimiento findByMonopatinId(Long monopatinId);
}
