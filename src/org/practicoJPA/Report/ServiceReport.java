package org.practicoJPA.Report;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.practicoJPA.dto.ReporteDTO;

public class ServiceReport {

	
	private EntityManager em;
	
	public ServiceReport(EntityManager em) {
		this.em = em;
	}
	
	/*
	 Deberia Retonar por ejem: 
	 ReporteDTO [NombreCarrera=Sistemas, anioingreso=2020, anioegreso=0, CantInscriptos=3, CantEgregesados=0]
	 ReporteDTO [NombreCarrera=TUDAI, anioingreso=2020, anioegreso=2026, CantInscriptos=3, CantEgregesados=3]
	 */
	public List<ReporteDTO> getReport() {
		TypedQuery<ReporteDTO> l = this.em.createQuery("SELECT new org.practicoJPA.dto.ReporteDTO(c.NombreCarrera,ec.inscripcion,ec.graduacion,COUNT(ec.inscripcion),COUNT(CASE WHEN ec.graduacion <> 0 THEN 1 ELSE null END)) FROM Carrera c JOIN c.carreras ec GROUP BY ec.inscripcion, ec.graduacion ORDER BY c.NombreCarrera ASC, ec.graduacion ASC", ReporteDTO.class);
		return l.getResultList();
	}
	
}
