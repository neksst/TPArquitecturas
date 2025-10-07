package org.practicoJPA.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Estudiante {
	@Id
	@Column(name="DNI")
	private Long DNI;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String apellido;
	@Column(nullable=false)
	private int edad;
	@Column(nullable=false)
	private String genero;
	@Column(nullable=false)
	private String ciudad;
	@Column(nullable=false)
	private Long lu;
	@OneToMany(mappedBy ="estudiante")
	private List<EstudianteCarrera> carreras = new ArrayList<>();
	
	public Estudiante() {
		
	}
	
	

	public Estudiante(Long dNI, String nombre, String apellido, int edad, String genero, String ciudad, Long lU) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.ciudad = ciudad;
		this.lu = lU;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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
		return lu;
	}

	public void setLU(Long lU) {
		this.lu = lU;
	}

	public Long getDNI() {
		return DNI;
	}



	public List<EstudianteCarrera> getCarreras() {
		return carreras;
	}



	public void setCarreras(List<EstudianteCarrera> carreras) {
		this.carreras = carreras;
	}



	@Override
	public String toString() {
		return "Estudiante [DNI=" + DNI + ", nombre=" + nombre + ", apellido=" + apellido + ", Edad=" + edad
				+ ", genero=" + genero + ", Ciudad=" + ciudad + ", LU=" + lu + ", carreras=" + carreras + "]";
	}

	

}
