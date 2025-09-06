package org.practico.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.practico.dao.interfaces.ClienteDAO;
import org.practico.entities.Cliente;

public class MYSQLClienteDAO implements ClienteDAO {

	private Connection con;
	
	public MYSQLClienteDAO(Connection con) {
		this.con = con;
	}

	@Override
	public List<String> getClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente getCliente(int id) {
		Cliente c = new Cliente();
		String select = "SELECT * FROM Cliente WHERE idCliente = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(select);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c.setId(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setEmail(rs.getString(3));
			}
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
	}

	@Override
	public void insertCliente(int id, String Nombre,String Email) {
		String Cliente = "INSERT INTO Cliente (idCliente,Nombre,Email) VALUES (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(Cliente);
			ps.setInt(1, id);
			ps.setString(2, Nombre);
			ps.setString(3, Email);
			ps.executeUpdate();
			ps.close();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
