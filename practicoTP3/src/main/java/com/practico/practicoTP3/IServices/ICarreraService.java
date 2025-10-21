package com.practico.practicoTP3.IServices;

import java.util.List;

import com.practico.practicoTP3.Entities.Carrera;
import com.practico.practicoTP3.dto.CarreraDTO;

public interface ICarreraService {

	Carrera addCarrera(Carrera c);
	List<CarreraDTO> getCarrerasInscriptos();
	
}
