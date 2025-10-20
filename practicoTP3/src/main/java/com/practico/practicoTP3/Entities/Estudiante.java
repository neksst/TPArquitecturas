package com.practico.practicoTP3.Entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Estudiante {

    @Id
    private Long dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;
    private Long lu;

    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteCarrera> carreras = new ArrayList<>();

    // 👇 Constructor vacío público (requerido por Hibernate)
    public Estudiante() {}

    public Estudiante(Long dni, String nombre, String apellido, int edad, String genero, String ciudad, Long lu) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.lu = lu;
    }
    
    
    /*
	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
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

	public Long getLu() {
		return lu;
	}

	public void setLu(Long lu) {
		this.lu = lu;
	}

	public List<EstudianteCarrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<EstudianteCarrera> carreras) {
		this.carreras = carreras;
	}*/

	@Override
	public String toString() {
		return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
				+ ", genero=" + genero + ", ciudad=" + ciudad + ", lu=" + lu + ", carreras=" + carreras + "]";
	}
    
    
}