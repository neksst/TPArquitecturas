import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.practico.dao.factory.DAOfactory;
import org.practico.dao.interfaces.ClienteDAO;

public class Servicio {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		DAOfactory mysqlDAO = DAOfactory.getDAOfactory(1);
		mysqlDAO.connect();
		//mysqlDAO.loadDB();
		ClienteDAO ClienteDAO = mysqlDAO.getCLienteDAO();
		System.out.println(ClienteDAO.getCliente(1));
		loadClients(ClienteDAO);
		System.out.println(ClienteDAO.getCliente(1));
	}
	
	public static void loadClients(ClienteDAO c) {

		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/clientes.csv"));
			for(CSVRecord row: parser) {
				c.insertCliente(
			            Integer.parseInt(row.get("idCliente")),
			            row.get("nombre"),
			            row.get("email")
			        );
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}

}
