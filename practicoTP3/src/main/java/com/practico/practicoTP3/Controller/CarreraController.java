package com.practico.practicoTP3.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practico.practicoTP3.Entities.Carrera;
import com.practico.practicoTP3.Services.CarreraService;
import com.practico.practicoTP3.dto.CarreraDTO;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

	@Autowired
	private CarreraService CarreraService;
	
	/*@GetMapping(produces="aplication/json")
	public List<CarreraDTO> getCarrerasInscriptos(){
		
		return this.CarreraService.getCarrerasInscriptos();
	}*/
	
	
	@PostMapping
	public void addCarrera(@RequestBody Carrera c) {
		this.CarreraService.addCarrera(c);
	}
	
	
	//Obtener inscriptos por carrera ordenados
	@GetMapping(value = "/inscriptos",produces="application/json")
	public List<CarreraDTO> getInscriptos() {
		return this.CarreraService.getCarrerasInscriptos();
	}
	
	
	
}
