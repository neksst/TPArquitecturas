package org.practico.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.practico.dao.interfaces.FacturaDAO;
import org.practico.entities.Factura;

public class MYSQLFacturaDAO implements FacturaDAO {
	
	private Connection con;
	
	public MYSQLFacturaDAO(Connection con) {
		this.con = con;
	}

	@Override
	public void insertFactura(int idFactura, int idCliente) {
		String insert = "INSERT INTO FACTURA (idFactura,idCliente) VALUES (?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(insert);
			ps.setInt(1,idFactura);
			ps.setInt(2, idCliente);
			ps.executeUpdate();
			ps.close();
			con.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Factura getFactura(int idFactura) {
		Factura f = new Factura();
		String select = "SELECT * FROM FACTURA WHERE idFactura = ? ";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(select);
			ps.setInt(1, idFactura);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				f.setIdFactura(idFactura);
				f.setIdCliente(rs.getInt(2));
			}
			return f;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
}
