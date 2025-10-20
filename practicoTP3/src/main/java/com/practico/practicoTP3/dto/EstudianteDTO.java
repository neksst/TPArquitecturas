package com.practico.practicoTP3.dto;

public class EstudianteDTO {

	private Long DNI;
	private String nombreCompleto;
	private int edad;
	private String genero;
	private String ciudad;
	private Long LU;
	
	public EstudianteDTO() {
		super();
	}
	public EstudianteDTO(Long dNI, String nombreCompleto, int edad, String genero, String ciudad, Long lU) {
		super();
		DNI = dNI;
		this.nombreCompleto = nombreCompleto;
		this.edad = edad;
		this.genero = genero;
		this.ciudad = ciudad;
		LU = lU;
	}
	
	public Long getDNI() {
		return DNI;
	}
	public void setDNI(Long dNI) {
		DNI = dNI;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Long getLU() {
		return LU;
	}
	public void setLU(Long lU) {
		LU = lU;
	}

	@Override
	public String toString() {
		return "EstudianteDTO [DNI=" + DNI + ", nombreCompleto=" + nombreCompleto + ", edad=" + edad + ", genero="
				+ genero + ", ciudad=" + ciudad + ", LU=" + LU + "]";
	}
	
	
	
	
}