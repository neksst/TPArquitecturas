package com.practico.practicoTP3.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Carrera {

	@Id
	@Column(name="ID")
	private Long ID;
	@Column(name="NombreCarrera")
	private String nombreCarrera;
	@Column(name="duracion")
	private int duracion;
	@OneToMany(mappedBy = "carrera")
	private List<EstudianteCarrera> carreras;
	

	public Carrera() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Carrera(Long id, String NombreCarrera,int duracion) {
		super();
		this.ID = id;
		this.nombreCarrera = NombreCarrera;
		this.duracion = duracion;
	}




	@Override
	public String toString() {
		return "Carrera [id=" + ID + ", NombreCarrera=" + nombreCarrera + ", duracion=" + duracion + ", carreras="
				+ carreras + "]";
	}

}