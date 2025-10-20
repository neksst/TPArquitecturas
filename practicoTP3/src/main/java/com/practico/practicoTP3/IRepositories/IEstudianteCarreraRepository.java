package com.practico.practicoTP3.IRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practico.practicoTP3.Entities.EstudianteCarrera;
import com.practico.practicoTP3.dto.ReporteDTO;

public interface IEstudianteCarreraRepository extends JpaRepository<EstudianteCarrera,Long> {

	@Query("SELECT new com.practico.practicoTP3.dto.ReporteDTO(c.nombreCarrera,ec.inscripcion,COUNT(ec),SUM(CASE WHEN ec.graduacion <> 0 THEN 1 ELSE 0 END)) FROM Carrera c JOIN c.carreras ec GROUP BY c.nombreCarrera, ec.inscripcion ORDER BY c.nombreCarrera ASC, ec.inscripcion ASC")
	public List<ReporteDTO> getReport();
	
}