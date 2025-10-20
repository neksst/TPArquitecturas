package com.practico.practicoTP3.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import com.practico.practicoTP3.Entities.Estudiante;
import com.practico.practicoTP3.IRepositories.IEstudianteRepository;
import com.practico.practicoTP3.IServices.IEstudianteService;
import com.practico.practicoTP3.dto.EstudianteDTO;

import jakarta.transaction.Transactional;

@Service
public class EstudianteService implements IEstudianteService{
	
	@Autowired
	private IEstudianteRepository EstudianteRepository;

	@Transactional
	@Override
	public Estudiante insertEstudiante(Estudiante e) {
		return this.EstudianteRepository.save(e);
		
	}

	@Override
	public List<EstudianteDTO> getEstudiantes() {
		return this.EstudianteRepository.getEstudiantes();
	}

	@Override
	public List<EstudianteDTO> getEstudiantesOrder(String order) {
	    Sort sort;

	    if ("desc".equalsIgnoreCase(order)) {
	        sort = Sort.by(Sort.Direction.DESC, "nombre");
	    } else {
	        sort = Sort.by(Sort.Direction.ASC, "nombre");
	    }

	    return this.EstudianteRepository.findAll(sort)
	            .stream()
	            .map(e -> new EstudianteDTO(
	                    e.getDni(),
	                    e.getNombre() + " " + e.getApellido(),
	                    e.getEdad(),
	                    e.getGenero(),
	                    e.getCiudad(),
	                    e.getLu()))
	            .toList();
	}

	
	@Override
	public EstudianteDTO getEstudianteByLU(Long LU) {
		return this.EstudianteRepository.getEstudianteByLU(LU);
	}

	@Override
	public List<EstudianteDTO> getEstudiantesByGender(String genero) {
		return this.EstudianteRepository.getEstudiantesByGender(genero);
	}

	@Override
	public List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String nombreCarrera, String ciudad) {
		return this.EstudianteRepository.getEstudiantesByCarreraAndCiudad(nombreCarrera,ciudad);
	}



	

}
