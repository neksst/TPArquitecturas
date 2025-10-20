package org.practicoJPA.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrera {

	@Id
	@Column(name="ID")
	private Long id;
	@Column(nullable = false)
	private String NombreCarrera;
	@Column(nullable = false)
	private int duracion;
	@OneToMany(mappedBy = "carrera")
	private List<EstudianteCarrera> carreras;
	

	public Carrera() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Carrera(Long id, String NombreCarrera,int duracion) {
		super();
		this.id = id;
		this.NombreCarrera = NombreCarrera;
		this.duracion = duracion;
	}



	public String getNombreCarrera() {
		return NombreCarrera;
	}



	public void setNombreCarrera(String nombreCarrera) {
		NombreCarrera = nombreCarrera;
	}



	public List<EstudianteCarrera> getCarreras() {
		return carreras;
	}



	public void setCarreras(List<EstudianteCarrera> carreras) {
		this.carreras = carreras;
	}



	public Long getId() {
		return id;
	}



	public int getDuracion() {
		return duracion;
	}



	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}



	@Override
	public String toString() {
		return "Carrera [id=" + id + ", NombreCarrera=" + NombreCarrera + ", duracion=" + duracion + ", carreras="
				+ carreras + "]";
	}



	


	
}
