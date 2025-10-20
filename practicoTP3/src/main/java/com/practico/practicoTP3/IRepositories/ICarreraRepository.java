package com.practico.practicoTP3.IRepositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practico.practicoTP3.Entities.Carrera;
import com.practico.practicoTP3.dto.CarreraDTO;

public interface ICarreraRepository extends JpaRepository<Carrera,Long>{


	
	@Query("SELECT new com.practico.practicoTP3.dto.CarreraDTO(c.id, c.nombreCarrera, COUNT(ec) AS INSCRIPTOS) FROM Carrera c JOIN c.carreras ec WHERE ec.graduacion = 0 GROUP BY c.id, c.nombreCarrera ORDER BY INSCRIPTOS")
	List<CarreraDTO> getCarrerasInscriptos();
	
	
}
