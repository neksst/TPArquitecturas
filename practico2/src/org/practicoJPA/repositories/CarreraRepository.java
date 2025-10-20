package org.practicoJPA.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.practicoJPA.IRepositories.ICarreraRepository;
import org.practicoJPA.dto.CarreraDTO;
import org.practicoJPA.entities.Carrera;

public class CarreraRepository implements ICarreraRepository {

	private EntityManager em;
	
	public CarreraRepository(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public void addCarrera(Carrera c) {
		this.em.persist(c);
		
	}

	@Override
	public List<CarreraDTO> getCarrerasInscriptos() {
		/*TypedQuery<CarreraDTO> l = this.em.createQuery("SELECT new org.practicoJPA.dto.CarreraDTO(c.id, c.NombreCarrera,COUNT(c)) FROM Carrera c JOIN c.carreras ec WHERE ec.graduacion = 0 ", CarreraDTO.class);
		return l.getResultList();*/
		TypedQuery<CarreraDTO> q = this.em.createQuery(
			    "SELECT new org.practicoJPA.dto.CarreraDTO(" +
			    "c.id, c.NombreCarrera, COUNT(ec) AS INSCRIPTOS) " +
			    "FROM Carrera c " +
			    "JOIN c.carreras ec " +
			    "WHERE ec.graduacion = 0 " +
			    "GROUP BY c.id, c.NombreCarrera " +
			    "ORDER BY INSCRIPTOS",
			    CarreraDTO.class
			);

			return q.getResultList();
	}

}
