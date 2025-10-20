package com.practico.practicoTP3.IServices;

import java.util.List;

import com.practico.practicoTP3.Entities.Estudiante;
import com.practico.practicoTP3.dto.EstudianteDTO;

public interface IEstudianteService {

	Estudiante insertEstudiante(Estudiante e);
	List<EstudianteDTO> getEstudiantes();
	List<EstudianteDTO> getEstudiantesOrder(String order);
	EstudianteDTO getEstudianteByLU(Long  LU);
	List<EstudianteDTO> getEstudiantesByGender(String genero);
	List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String nombreCarrera, String ciudad);
}
