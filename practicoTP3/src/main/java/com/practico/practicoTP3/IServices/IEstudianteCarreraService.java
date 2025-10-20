package com.practico.practicoTP3.IServices;

import java.util.List;

import com.practico.practicoTP3.Entities.EstudianteCarrera;
import com.practico.practicoTP3.dto.ReporteDTO;

public interface IEstudianteCarreraService {
	
	void matricularEstudiante(EstudianteCarrera e);
	public List<ReporteDTO> getReport();
}
