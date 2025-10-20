package com.practico.practicoTP3.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practico.practicoTP3.Entities.EstudianteCarrera;
import com.practico.practicoTP3.Services.EstudianteCarreraService;
import com.practico.practicoTP3.dto.ReporteDTO;

@RestController
@RequestMapping("/matriculas")
public class EstudianteCarreraController {
	
	@Autowired
	private EstudianteCarreraService service;
	
	@PostMapping
	public void matricularEstudiante(@RequestBody EstudianteCarrera e) {
		this.service.matricularEstudiante(e);
	}
	
	@GetMapping(value = "/report", produces="application/json")
	public List<ReporteDTO> getReport(){
		return this.service.getReport();
	}
}
