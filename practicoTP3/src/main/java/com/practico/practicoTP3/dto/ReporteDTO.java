package com.practico.practicoTP3.dto;

public class ReporteDTO {

	
	 private String nombreCarrera;
	    private Integer anio;
	    private Long cantidadInscriptos;
	    private Long cantidadEgresados;

	    public ReporteDTO(String nombreCarrera, Integer anio, Long cantidadInscriptos, Long cantidadEgresados) {
	        this.nombreCarrera = nombreCarrera;
	        this.anio = anio;
	        this.cantidadInscriptos = cantidadInscriptos;
	        this.cantidadEgresados = cantidadEgresados;
	    }

		public String getNombreCarrera() {
			return nombreCarrera;
		}

		public void setNombreCarrera(String nombreCarrera) {
			this.nombreCarrera = nombreCarrera;
		}

		public Integer getAnio() {
			return anio;
		}

		public void setAnio(Integer anio) {
			this.anio = anio;
		}

		public Long getCantidadInscriptos() {
			return cantidadInscriptos;
		}

		public void setCantidadInscriptos(Long cantidadInscriptos) {
			this.cantidadInscriptos = cantidadInscriptos;
		}

		public Long getCantidadEgresados() {
			return cantidadEgresados;
		}

		public void setCantidadEgresados(Long cantidadEgresados) {
			this.cantidadEgresados = cantidadEgresados;
		}
	    
	    

	
	
	
	
}