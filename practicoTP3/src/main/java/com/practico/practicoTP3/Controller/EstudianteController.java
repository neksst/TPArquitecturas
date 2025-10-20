package com.practico.practicoTP3.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practico.practicoTP3.Entities.Carrera;
import com.practico.practicoTP3.Entities.Estudiante;
import com.practico.practicoTP3.Services.EstudianteService;
import com.practico.practicoTP3.dto.EstudianteDTO;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

	@Autowired
	private EstudianteService service;
	
	
	@PostMapping(consumes = "application/json")
	public Estudiante insertEstudiante(@RequestBody Estudiante e) {
		System.out.println(e); //Debbug para comprobar si el objeto es recibido
		return this.service.insertEstudiante(e);
	}
	
	@GetMapping(produces = "application/json")
	public List<EstudianteDTO>getEstudiantes(){
		return this.service.getEstudiantes();
	}
	
	
	/*
	 * Obtener los estudiantes ordenados por nombre
	 * endopoint -> /estudiantes?order=desc/asc
	 * Orden por defecto: desc
	 */
	@GetMapping(params = "order", produces = "application/json")
	public List<EstudianteDTO> getEstudiantesOrder(@RequestParam("order") String order){
		return this.service.getEstudiantesOrder(order);
	}
	
	
	
	//Obtener Estudiante por LU /estudiante?lu={lu}
	@GetMapping(params = "lu",produces="application/json")
	public ResponseEntity<EstudianteDTO> getEstudianteByLu(@RequestParam("lu") Long lu) {
		EstudianteDTO r = this.service.getEstudianteByLU(lu);
		if(r == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(r);
	}
	
	//obtener Estudiantes en base a su genero
	@GetMapping(params = "gender", produces = "application/json")
	public List<EstudianteDTO> getEstudianteByGender(@RequestParam("gender") String gender) {
		return this.service.getEstudiantesByGender(gender);
	}
	
	/*
	 * obtener Estudiantes en base a la carrera que estudian y la ciudad de origen
	 * endpoint -> /filtrto?carrera={carrera}&ciudad={ciudad}
	 */
	@GetMapping(value= "/filtro",produces = "application/json")
	public List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(@RequestParam("carrera") String carrera, @RequestParam("ciudad") String ciudad){
		return this.service.getEstudiantesByCarreraAndCiudad(carrera.toLowerCase(), ciudad.toLowerCase());
	}
}
