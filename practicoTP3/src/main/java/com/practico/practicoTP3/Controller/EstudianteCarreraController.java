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

import com.practico.practicoTP3.Entities.EstudianteCarrera;
import com.practico.practicoTP3.Services.EstudianteCarreraService;
import com.practico.practicoTP3.dto.MatriculaDTO;
import com.practico.practicoTP3.dto.ReporteDTO;

@RestController
@RequestMapping("/matriculas")
public class EstudianteCarreraController {
	
	@Autowired
	private EstudianteCarreraService service;
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<MatriculaDTO> matricularEstudiante(@RequestBody EstudianteCarrera e) {
		EstudianteCarrera r = this.service.matricularEstudiante(e);
		if(r  == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		MatriculaDTO m = new MatriculaDTO(e.getEstudiante().getGenero() + " " + e.getEstudiante().getApellido(),
										  e.getCarrera().getNombreCarrera(),
										  e.getInscripcion(),
										  e.getGraduacion(),
										  e.getAntiguedad());
		return ResponseEntity.ok(m);
										  
		
	}
	
	@GetMapping(value = "/report", produces="application/json")
	public ResponseEntity<List<ReporteDTO>> getReport(){
		List<ReporteDTO> r = this.service.getReport();
		if(r.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(r);
	}
}
