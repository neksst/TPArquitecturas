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
    
    

	@Override
	public String toString() {
		return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
				+ ", genero=" + genero + ", ciudad=" + ciudad + ", lu=" + lu + ", carreras=" + carreras + "]";
	}
    
    
}