package com.practico.practicoTP3.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practico.practicoTP3.Entities.Carrera;
import com.practico.practicoTP3.IRepositories.ICarreraRepository;
import com.practico.practicoTP3.IServices.ICarreraService;
import com.practico.practicoTP3.dto.CarreraDTO;

@Service
public class CarreraService implements ICarreraService {

	@Autowired
	private ICarreraRepository CarreraRepository;
	
	@Override
	public void addCarrera(Carrera c) {
		this.CarreraRepository.save(c);
		
	}

	/*@Override
	public List<CarreraDTO> getCarrerasInscriptos() {
		// TODO Auto-generated method stub
		return this.CarreraRepository.getCarrerasInscriptos();
	}
	*/

	@Override
	public List<CarreraDTO> getCarrerasInscriptos() {
		return this.CarreraRepository.getCarrerasInscriptos();
	}
	
	

}
