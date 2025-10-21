package com.practico.practicoTP3.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practico.practicoTP3.Entities.Estudiante;
import com.practico.practicoTP3.Services.EstudianteService;
import com.practico.practicoTP3.dto.EstudianteDTO;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

	@Autowired
	private EstudianteService service;
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<EstudianteDTO> insertEstudiante(@RequestBody Estudiante e) {
		System.out.println(e); //Debbug para comprobar si el objeto es recibido
		Estudiante r = this.service.insertEstudiante(e);
		if(r == null)
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		EstudianteDTO dto = new EstudianteDTO(e.getDni(),e.getNombre() + " " + e.getApellido(), e.getEdad(), e.getGenero(), e.getCiudad(), e.getLu());
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<EstudianteDTO>> getEstudiantes(){
		List<EstudianteDTO> r =  this.service.getEstudiantes();
		if(r.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(r);
	}
	
	
	/*
	 * Obtener los estudiantes ordenados por nombre
	 * endopoint -> /estudiantes?order=desc/asc
	 * Orden por defecto: desc
	 */
	@GetMapping(params = "order", produces = "application/json")
	public ResponseEntity<List<EstudianteDTO>> getEstudiantesOrder(@RequestParam("order") String order){
		List<EstudianteDTO> r = this.service.getEstudiantesOrder(order);
		if(r.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(r);
	}
	
	
	
	//Obtener Estudiante por LU /estudiante?lu={lu}
	@GetMapping(params = "lu",produces="application/json")
	public ResponseEntity<EstudianteDTO> getEstudianteByLu(@RequestParam("lu") Long lu) {
		EstudianteDTO r = this.service.getEstudianteByLU(lu);
		if(r == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(r);
	}
	
	//obtener Estudiantes en base a su genero
	@GetMapping(params = "gender", produces = "application/json")
	public ResponseEntity<List<EstudianteDTO>> getEstudianteByGender(@RequestParam("gender") String gender) {
		List<EstudianteDTO> r = this.service.getEstudiantesByGender(gender);
		if(r.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(r);
	}
	
	/*
	 * obtener Estudiantes en base a la carrera que estudian y la ciudad de origen
	 * endpoint -> /filtrto?carrera={carrera}&ciudad={ciudad}
	 */
	@GetMapping(value= "/filtro",produces = "application/json")
	public ResponseEntity<List<EstudianteDTO>> getEstudiantesByCarreraAndCiudad(@RequestParam("carrera") String carrera, @RequestParam("ciudad") String ciudad){
		List<EstudianteDTO> r = this.service.getEstudiantesByCarreraAndCiudad(carrera.toLowerCase(), ciudad.toLowerCase());
		if(r.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(r);
	}
}
