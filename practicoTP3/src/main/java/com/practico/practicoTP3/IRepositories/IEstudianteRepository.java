package com.practico.practicoTP3.IRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practico.practicoTP3.Entities.Estudiante;
import com.practico.practicoTP3.dto.EstudianteDTO;

public interface IEstudianteRepository extends JpaRepository<Estudiante,Long> {;
	
	@Query("SELECT new com.practico.practicoTP3.dto.EstudianteDTO(e.dni,CONCAT(e.nombre, ' ', e.apellido), e.edad, e.genero, e.ciudad, e.lu) FROM Estudiante e")
	List<EstudianteDTO> getEstudiantes();
	
	@Query("SELECT new com.practico.practicoTP3.dto.EstudianteDTO(e.dni,CONCAT(e.nombre, ' ', e.apellido), e.edad, e.genero, e.ciudad, e.lu) FROM Estudiante e WHERE e.lu = :LU")
	EstudianteDTO getEstudianteByLU(Long  LU);
	
	@Query("SELECT new com.practico.practicoTP3.dto.EstudianteDTO(e.dni,CONCAT(e.nombre, ' ', e.apellido), e.edad, e.genero, e.ciudad, e.lu) FROM Estudiante e WHERE e.genero = :genero")
	List<EstudianteDTO> getEstudiantesByGender(String genero);
	
	@Query("SELECT new com.practico.practicoTP3.dto.EstudianteDTO(e.dni,CONCAT(e.nombre, ' ', e.apellido), e.edad, e.genero, e.ciudad, e.lu) FROM Estudiante e JOIN e.carreras ci JOIN ci.carrera c WHERE LOWER(c.nombreCarrera) = :nombreCarrera AND LOWER(e.ciudad) = :ciudad")
	List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String nombreCarrera, String ciudad);
	
}