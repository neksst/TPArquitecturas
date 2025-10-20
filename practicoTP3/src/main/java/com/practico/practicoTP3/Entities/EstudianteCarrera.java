package com.practico.practicoTP3.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class EstudianteCarrera {

	@Id
	@Column(name="ID")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DNI_Estudiante")
	private Estudiante estudiante;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Carrera")
	private Carrera carrera;
	private int inscripcion;
	private int graduacion;
	private int antiguedad;
	
	public EstudianteCarrera() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstudianteCarrera(Long id, Estudiante estudiante, Carrera carrera, int inscripcion, int graduacion,
			int antiguedad) {
		super();
		this.id = id;
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.inscripcion = inscripcion;
		this.graduacion = graduacion;
		this.antiguedad = antiguedad;
	}

	
	
}