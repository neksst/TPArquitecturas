package org.practico.dao.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.practico.dao.daos.DERBYClienteDAO;
import org.practico.dao.daos.DERBYFacturaDAO;
import org.practico.dao.daos.DERBYFacturaProductoDAO;
import org.practico.dao.daos.DERBYProductoDAO;
import org.practico.dao.interfaces.ClienteDAO;
import org.practico.dao.interfaces.FacturaDAO;
import org.practico.dao.interfaces.FacturaProductoDAO;
import org.practico.dao.interfaces.ProductoDAO;

public class DERBYDAOFactory  extends DAOfactory{
	final static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	final static String URI    = "jdbc:derby:DerbyDB;create=true"; 

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
	
	
	@Override
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URI);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	@Override
	public ClienteDAO getCLienteDAO() {
		return new DERBYClienteDAO(this.getConnection());
	}

	@Override
	public ProductoDAO getProductoDAO() {
		return new DERBYProductoDAO(this.getConnection());
	}

	@Override
	public FacturaDAO getFacturaDAO() {
		return new DERBYFacturaDAO(this.getConnection());
	}

	@Override
	public FacturaProductoDAO getFacturaProductoDAO() {
		return new DERBYFacturaProductoDAO(this.getConnection());
	}

	@Override
	public void loadDB() {
		Connection c = getConnection();

		String Cliente = "CREATE TABLE CLIENTE (" +
		        "idCliente INT NOT NULL," +
		        "Nombre VARCHAR(30) NOT NULL," +
		        "email VARCHAR(150) NOT NULL," +
		        "PRIMARY KEY (idCliente)" +
		        ")";

		String Factura = "CREATE TABLE FACTURA (" +
		        "idFactura INT NOT NULL," +
		        "idCliente INT NOT NULL references CLIENTE(idCliente)," +
		        "PRIMARY KEY (idFactura)" +
		        ")";

		String Producto = "CREATE TABLE PRODUCTO (" +
		        "idProducto INT NOT NULL," +
		        "Nombre VARCHAR(30)," +
		        "Valor INT," +
		        "PRIMARY KEY (idProducto)" +
		        ")";

		String Producto_Factura = "CREATE TABLE FACTURA_PRODUCTO (" +
		        "idProducto INT NOT NULL references PRODUCTO(idProducto)," +
		        "idFactura INT NOT NULL  references FACTURA(idFactura)," +
		        "Cantidad INT," +
		        "PRIMARY KEY (idProducto, idFactura)" +
		        ")";

		try {
			c.prepareStatement(Cliente).execute();
			c.commit();

			c.prepareStatement(Factura).execute();
			c.commit();

			c.prepareStatement(Producto).execute();
			c.commit();

			c.prepareStatement(Producto_Factura).execute();
			c.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
