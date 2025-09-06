import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.practico.dao.factory.DAOfactory;
import org.practico.dao.interfaces.ClienteDAO;
import org.practico.dao.interfaces.FacturaDAO;
import org.practico.dao.interfaces.FacturaProductoDAO;
import org.practico.dao.interfaces.ProductoDAO;

public class Servicio {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		DAOfactory mysqlDAO = DAOfactory.getDAOfactory(1);
		//mysqlDAO.loadDB();
		ClienteDAO         ClienteDAO   	  = mysqlDAO.getCLienteDAO();
		ProductoDAO 	   ProductoDAO        = mysqlDAO.getProductoDAO();
		FacturaDAO         FacturaDAO         = mysqlDAO.getFacturaDAO();
		FacturaProductoDAO FacturaProductoDAO = mysqlDAO.getFacturaProductoDAO();
		//System.out.println(ClienteDAO.getCliente(1));
		//loadClients(ClienteDAO);
		//loadProducts(ProductoDAO);
		//loadFacturas(FacturaDAO);
		//loadFactura_Productos(FacturaProductoDAO);
		/*System.out.println(ClienteDAO.getCliente(1));
		System.out.println(ProductoDAO.getProducto(1));
		System.out.println(FacturaDAO.getFactura(1));
		for(String s : ProductoDAO.getProductos()) {
			System.out.println(s);
		}*/
		System.out.println(ProductoDAO.getProductoMasVendido());
		for(String c : ClienteDAO.getClientesMasFacturados()) {
			System.out.println(c);
		}
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
	
	public static void loadProducts(ProductoDAO p) {

		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/productos.csv"));
			for(CSVRecord row: parser) {
				p.insertProducto(
			            Integer.parseInt(row.get("idProducto")),
			            row.get("nombre"),
			            Integer.parseInt(row.get("valor"))
			        );
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	
	public static void loadFacturas(FacturaDAO f) {

		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/facturas.csv"));
			for(CSVRecord row: parser) {
				f.insertFactura(
			            Integer.parseInt(row.get("idFactura")),
			            Integer.parseInt(row.get("idCliente"))
			        );
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	
	public static void loadFactura_Productos(FacturaProductoDAO f) {

		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new
					FileReader("src/facturas-productos.csv"));
			for(CSVRecord row: parser) {
				f.insertFacturaProducto(
			            Integer.parseInt(row.get("idFactura")),
			            Integer.parseInt(row.get("idProducto")),
			            Integer.parseInt(row.get("cantidad"))
			        );
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}

}
