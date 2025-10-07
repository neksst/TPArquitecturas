package org.practicoJPA.IRepositories;

import java.util.List;

import org.practicoJPA.dto.CarreraDTO;
import org.practicoJPA.entities.Carrera;

public interface ICarreraRepository {

	void addCarrera(Carrera c);
	List<CarreraDTO> getCarrerasInscriptos();
}
