package org.practicoJPA.IRepositories;

import org.practicoJPA.entities.Estudiante;
import java.util.List;
import org.practicoJPA.dto.EstudianteDTO;

public interface IEstudianteRepository {
	void insertEstudiante(Estudiante e);
	List<EstudianteDTO> getEstudiantes();
	EstudianteDTO getEstudianteByLU(Long  LU);
	List<EstudianteDTO> getEstudiantesByGender(String genero);
	List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(int carreraid, String ciudad);
	
}
