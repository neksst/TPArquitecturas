package org.practicoJPA.repositories;

import javax.persistence.EntityManager;

import org.practicoJPA.IRepositories.IEstudianteCarreraRepository;
import org.practicoJPA.entities.EstudianteCarrera;

public class EstudianteCarreraRepository implements IEstudianteCarreraRepository{

	private EntityManager em;
	
	public EstudianteCarreraRepository(EntityManager em) {
		super();
		this.em = em;
	}


	@Override
	public void matricularEstudiante(EstudianteCarrera e) {
		em.persist(e);
	}

}
