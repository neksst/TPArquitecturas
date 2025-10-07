package org.practicoJPA.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public int getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(int inscripcion) {
		this.inscripcion = inscripcion;
	}

	public int getGraduacion() {
		return graduacion;
	}

	public void setGraduacion(int graduacion) {
		this.graduacion = graduacion;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public Long getId() {
		return id;
	}
	
	
	
	
	
	
	
	
}
