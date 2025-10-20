package org.practicoJPA.dto;

public class ReporteDTO {

	
	private String NombreCarrera;
	private int anioingreso;
	private int anioegreso;
	private Long CantInscriptos;
	private Long CantEgregesados;
	
	public ReporteDTO(String NombreCarrera,int anioingreso,int anioegreso, Long CantInscriptos, Long CantEgresados) {
		this.NombreCarrera   = NombreCarrera;
		this.anioingreso 	 = anioingreso;
		this.anioegreso		 = anioegreso;
		this.CantInscriptos  = CantInscriptos;
		this.CantEgregesados = CantEgresados;
	}

	public String getNombreCarrera() {
		return NombreCarrera;
	}

	public Long getCantInscriptos() {
		return CantInscriptos;
	}

	public Long getCantEgregesados() {
		return CantEgregesados;
	}

	public int getAnioingreso() {
		return anioingreso;
	}

	public int getAnioegreso() {
		return anioegreso;
	}

	@Override
	public String toString() {
		return "ReporteDTO [NombreCarrera=" + NombreCarrera + ", anioingreso=" + anioingreso + ", anioegreso="
				+ anioegreso + ", CantInscriptos=" + CantInscriptos + ", CantEgregesados=" + CantEgregesados + "]";
	}

	
	
	
	
}
