package com.practico.practicoTP3.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<CarreraDTO> addCarrera(@RequestBody Carrera c) {
		Carrera r = this.CarreraService.addCarrera(c);
		if(r == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		CarreraDTO cdto = new CarreraDTO(c.getID(),c.getNombreCarrera(),0L); //Se entiende que al subir una nueva carrera esta no tiene inscriptos
		return ResponseEntity.ok(cdto);
	}
	
	
	//Obtener inscriptos por carrera ordenados
	@GetMapping(value = "/inscriptos",produces="application/json")
	public ResponseEntity<List<CarreraDTO>> getInscriptos() {
		List<CarreraDTO> r = this.CarreraService.getCarrerasInscriptos();
		if(r.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(r);
	}
	
	
	
}
