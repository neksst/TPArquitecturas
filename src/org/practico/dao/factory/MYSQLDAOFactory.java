package org.practico.dao.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.practico.dao.daos.MYSQLClienteDAO;
import org.practico.dao.interfaces.ClienteDAO;
import org.practico.dao.interfaces.ProductoDAO;

public class MYSQLDAOFactory extends DAOfactory {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URI = "jdbc:mysql://localhost:3306/testPractico";
	private  Connection con = null; 

	static {
		try {
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void connect() {
		try {
			
			con = DriverManager.getConnection(URI, "root", "");
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		if(con == null) {
			connect();
		}
		return con;
	}

	@Override
	public ClienteDAO getCLienteDAO() {
		return new MYSQLClienteDAO(con);
	}

	@Override
	public ProductoDAO getProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public  void  loadDB() {
		String Cliente = "CREATE TABLE cliente (" +
				  "idCliente int(11) NOT NULL," +
				  "Nombre varchar(30) NOT NULL," +
				  "email varchar(150) NOT NULL," +
				  "PRIMARY KEY(idCliente));";
		
		String Factura = "CREATE TABLE Factura (" +
						 "idFactura int(11)," +
						 "idCliente int(11)," +
						 "PRIMARY KEY(idFactura));";
		
		String Producto = "CREATE TABLE Producto (" +
						  "idProducto int(11),"     +
						  "Nombre VARCHAR(30)," 	+
						  "Valor int(11),"      	+
						  "PRIMARY KEY(idProducto));";
		
		String Producto_Factura = "CREATE TABLE Producto_Factura (" +
								  "idProducto int(11)," 			+
								  "idFactura  int(11),"				+
								  "Cantidad int(11))";
		String FK_cliente_factura = "ALTER TABLE Factura " +
			    "ADD CONSTRAINT factura_ibfk_1 " +
			    "FOREIGN KEY (idCliente) REFERENCES cliente (idCliente);";
		
		String FK_Producto_Factura = "ALTER TABLE Producto_Factura " +
			    "ADD CONSTRAINT factura_producto_ibfk_1 FOREIGN KEY (idFactura) REFERENCES Factura (idFactura), " +
			    "ADD CONSTRAINT factura_producto_ibfk_2 FOREIGN KEY (idProducto) REFERENCES Producto (idProducto);";
		try {
			con.prepareStatement(Cliente).execute();
			con.commit();
			
			con.prepareCall(Factura).execute();
			con.commit();
			
			con.prepareCall(Producto).execute();
			con.commit();
			
			con.prepareCall(Producto_Factura).execute();
			con.commit();
			
			con.prepareCall(FK_cliente_factura).execute();
			con.commit();
			
			con.prepareCall(FK_Producto_Factura).execute();
			con.commit();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
