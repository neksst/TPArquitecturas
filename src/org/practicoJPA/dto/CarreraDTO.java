package org.practicoJPA.dto;

public class CarreraDTO {

	private Long id;
	private String NombreCarrera;
	private Long CantInscriptos;
	public CarreraDTO(Long id, String nombreCarrera,Long CantInscriptos) {
		super();
		this.id = id;
		NombreCarrera = nombreCarrera;
		this.CantInscriptos = CantInscriptos;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreCarrera() {
		return NombreCarrera;
	}
	public void setNombreCarrera(String nombreCarrera) {
		NombreCarrera = nombreCarrera;
	}
	public Long getCantInscriptos() {
		return CantInscriptos;
	}
	public void setCantInscriptos(Long cantInscriptos) {
		CantInscriptos = cantInscriptos;
	}
	@Override
	public String toString() {
		return "CarreraDTO [id=" + id + ", NombreCarrera=" + NombreCarrera + ", CantInscriptos=" + CantInscriptos + "]";
	}
	
	
	
}
