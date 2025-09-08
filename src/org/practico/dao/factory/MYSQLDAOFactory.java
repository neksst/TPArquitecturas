package org.practico.dao.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.practico.dao.daos.MYSQLClienteDAO;
import org.practico.dao.daos.MYSQLFacturaDAO;
import org.practico.dao.daos.MYSQLFacturaProductoDAO;
import org.practico.dao.daos.MYSQLProductoDAO;
import org.practico.dao.interfaces.ClienteDAO;
import org.practico.dao.interfaces.FacturaDAO;
import org.practico.dao.interfaces.FacturaProductoDAO;
import org.practico.dao.interfaces.ProductoDAO;

public class MYSQLDAOFactory extends DAOfactory {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URI = "jdbc:mysql://localhost:3306/testPractico";

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
			con = DriverManager.getConnection(URI, "root", "");
			con.setAutoCommit(false);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	@Override
	public ClienteDAO getCLienteDAO() {
		return new MYSQLClienteDAO(this.getConnection());
	}

	@Override
	public ProductoDAO getProductoDAO() {
		return new MYSQLProductoDAO(this.getConnection());
	}
	
	@Override
	public FacturaDAO getFacturaDAO() {
		return new MYSQLFacturaDAO(this.getConnection());
	}

	@Override
	public FacturaProductoDAO getFacturaProductoDAO() {
		return new MYSQLFacturaProductoDAO(this.getConnection());
	}


	@Override
	public void loadDB() {
		Connection c = getConnection();
		String Cliente = "CREATE TABLE IF NOT EXISTS cliente (" + "idCliente int(11) NOT NULL," + "Nombre varchar(30) NOT NULL,"
				+ "email varchar(150) NOT NULL," + "PRIMARY KEY(idCliente));";

		String Factura = "CREATE TABLE IF NOT EXISTS Factura (" + "idFactura int(11)," + "idCliente int(11),"
				+ "PRIMARY KEY(idFactura));";

		String Producto = "CREATE TABLE IF NOT EXISTS Producto (" + "idProducto int(11)," + "Nombre VARCHAR(30)," + "Valor int(11),"
				+ "PRIMARY KEY(idProducto));";

		String Producto_Factura = "CREATE TABLE IF NOT EXISTS Factura_Producto (" + "idProducto int(11)," + "idFactura  int(11),"
				+ "Cantidad int(11))";
		String FK_cliente_factura = "ALTER TABLE Factura " + "ADD CONSTRAINT factura_ibfk_1 "
				+ "FOREIGN KEY (idCliente) REFERENCES cliente (idCliente);";

		String FK_Factura_Producto = "ALTER TABLE Factura_Producto "
				+ "ADD CONSTRAINT factura_producto_ibfk_1 FOREIGN KEY (idFactura) REFERENCES Factura (idFactura), "
				+ "ADD CONSTRAINT factura_producto_ibfk_2 FOREIGN KEY (idProducto) REFERENCES Producto (idProducto);";
		try {
			c.prepareStatement(Cliente).execute();
			c.commit();

			c.prepareStatement(Factura).execute();
			c.commit();

			c.prepareStatement(Producto).execute();
			c.commit();

			c.prepareStatement(Producto_Factura).execute();
			c.commit();

			c.prepareStatement(FK_cliente_factura).execute();
			c.commit();

			c.prepareStatement(FK_Factura_Producto).execute();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
