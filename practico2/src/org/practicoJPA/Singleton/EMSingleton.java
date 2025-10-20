package org.practicoJPA.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMSingleton {
	private static EntityManagerFactory emf;
	
	public static void inicializar() {
		emf = Persistence.createEntityManagerFactory("PracticoJPA");
	}
	
	public static void cerrar() {
		emf.close();
	}
	
	public static EntityManager getEntityManager(){
		if(emf == null) {
			throw new IllegalStateException("EntityManager no inicilizado.");
		}
		return emf.createEntityManager();
	}
}
