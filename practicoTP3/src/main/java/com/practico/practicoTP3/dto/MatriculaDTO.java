package com.practico.practicoTP3.dto;

public class MatriculaDTO {
	
	private String nombreCompleto;
	private String carrera;
	private int inscipcion;
	private int egreso;
	private int antiguedad;
	
	public MatriculaDTO(String nombreCompleto, String carrera, int inscipcion, int egreso, int antiguedad) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.carrera = carrera;
		this.inscipcion = inscipcion;
		this.egreso = egreso;
		this.antiguedad = antiguedad;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public int getInscipcion() {
		return inscipcion;
	}
	public void setInscipcion(int inscipcion) {
		this.inscipcion = inscipcion;
	}
	public int getEgreso() {
		return egreso;
	}
	public void setEgreso(int egreso) {
		this.egreso = egreso;
	}
	public int getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
	
	
	
	

}
