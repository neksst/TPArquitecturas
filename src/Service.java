import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.practicoJPA.Report.ServiceReport;
import org.practicoJPA.Singleton.EMSingleton;
import org.practicoJPA.entities.Carrera;
import org.practicoJPA.entities.Estudiante;
import org.practicoJPA.entities.EstudianteCarrera;
import org.practicoJPA.repositories.CarreraRepository;
import org.practicoJPA.repositories.EstudianteCarreraRepository;
import org.practicoJPA.repositories.EstudianteRepository;

public class Service {

	public static void main(String[] args) {
		EMSingleton.inicializar();
		EntityManager em = EMSingleton.getEntityManager();
		em.getTransaction().begin();
		EstudianteRepository er = new EstudianteRepository(em);
		EstudianteCarreraRepository ec = new EstudianteCarreraRepository(em);
		CarreraRepository cr = new CarreraRepository(em);
		loadEstudiantes(er);
		/*
		loadCarreras(cr);
		loadEstudianteCarrera(ec);*/
		em.getTransaction().commit();
		
		System.out.println(er.getEstudiantes());
		/*System.out.println(er.getEstudianteByLU(888L));
		System.out.println(er.getEstudiantesByGender("cualquier frula"));
		
		ServiceReport sr = new ServiceReport(em);
		
		System.out.println(sr.getReport());
		
		System.out.println(cr.getCarrerasInscriptos());;*/
		em.close();
		EMSingleton.cerrar();
		
	}
	
	public static void loadEstudiantes(EstudianteRepository er) {

		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/cvs/estudiantes.csv"));
			for(CSVRecord row: parser) {
				er.insertEstudiante(new Estudiante(
					    Long.parseLong(row.get("DNI")),
					    row.get("nombre"),
					    row.get("apellido"),
					    Integer.parseInt(row.get("edad")),
					    row.get("genero"),
					    row.get("ciudad"),
					    Long.parseLong(row.get("LU"))
					));
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	public static void loadCarreras(CarreraRepository cr) {

		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/cvs/carreras.csv"));
			for(CSVRecord row: parser) {
				cr.addCarrera(new Carrera(
						Long.parseLong(row.get("id_carrera")),
						row.get("carrera"),
						Integer.parseInt(row.get("duracion"))));
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	
	public static void loadEstudianteCarrera(EstudianteCarreraRepository er) {

		CSVParser parser;
		EntityManager em = EMSingleton.getEntityManager();
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/cvs/estudianteCarrera.csv"));
			for(CSVRecord row: parser) {
				er.matricularEstudiante(new EstudianteCarrera(
					    Long.parseLong(row.get("id")),
					    em.find(Estudiante.class, Long.parseLong(row.get("id_estudiante"))),
					    em.find(Carrera.class, Long.parseLong(row.get("id_carrera"))),
					    Integer.parseInt(row.get("inscripcion")),
					    Integer.parseInt(row.get("graduacion")),
					    Integer.parseInt(row.get("antiguedad"))
					));
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}

}
