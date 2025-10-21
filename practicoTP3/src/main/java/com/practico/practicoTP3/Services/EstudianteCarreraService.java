package com.practico.practicoTP3.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practico.practicoTP3.Entities.EstudianteCarrera;
import com.practico.practicoTP3.IRepositories.IEstudianteCarreraRepository;
import com.practico.practicoTP3.IServices.IEstudianteCarreraService;
import com.practico.practicoTP3.dto.ReporteDTO;


@Service
public class EstudianteCarreraService implements IEstudianteCarreraService{
	
	@Autowired
	private IEstudianteCarreraRepository EstdianteCarreraRepository;

	@Override
	public EstudianteCarrera matricularEstudiante(EstudianteCarrera e) {
		return this.EstdianteCarreraRepository.save(e);
		
	}

	@Override
	public List<ReporteDTO> getReport() {
		return this.EstdianteCarreraRepository.getReport();
	}

}
