package org.practico.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.practico.dao.interfaces.FacturaProductoDAO;

public class MYSQLFacturaProductoDAO implements FacturaProductoDAO {

	private Connection con;
	
	public MYSQLFacturaProductoDAO(Connection con) {
		this.con = con;
	}

	@Override
	public void insertFacturaProducto(int idFactura, int idProducto, int cantidad) {
		String insert = "INSERT INTO Factura_Producto (idFactura,idProducto,cantidad) VALUES(?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(insert);
			ps.setInt(1, idFactura);
			ps.setInt(2, idProducto);
			ps.setInt(3, cantidad);
			ps.executeUpdate();
			ps.close();
			con.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
