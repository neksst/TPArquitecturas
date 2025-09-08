package org.practico.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.practico.dao.interfaces.ClienteDAO;
import org.practico.entities.Cliente;

public class DERBYClienteDAO implements ClienteDAO {
	private Connection con;

	public DERBYClienteDAO(Connection con) {
		this.con = con;
	}

	@Override
	public List<String> getClientes() {
		List<String> l = new LinkedList<String>();
		String select = "SELECT * FROM CLIENTE";
		PreparedStatement ps;
		try{
			ps = con.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l.add("Nombre: " + rs.getString(2));
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public Cliente getCliente(int id) {
		Cliente c = new Cliente();
		String select = "SELECT * FROM CLIENTE WHERE idCliente = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c.setId(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setEmail(rs.getString(3));
			}
			ps.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;

	}

	@Override
	public void insertCliente(int id, String Nombre, String Email) {
		String Cliente = "INSERT INTO CLIENTE (idCliente,Nombre,Email) VALUES (?,?,?)";
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
	
	@Override
	public List<String> getClientesMasFacturados(){
		List<String> l = new LinkedList<String>();
		String join = "SELECT c.Nombre AS Cliente, SUM(fp.Cantidad * p.Valor) AS TOTAL_FACTURADO FROM CLIENTE c JOIN FACTURA f ON c.idCliente = f.idCliente JOIN FACTURA_PRODUCTO fp ON f.idFactura = fp.idFactura JOIN PRODUCTO p ON p.idProducto = fp.idProducto GROUP BY c.Nombre ORDER BY TOTAL_FACTURADO DESC";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(join);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l.add("Cliente: " + rs.getString(1) + " Total: " + rs.getInt(2));
			}
			ps.close();
			return l;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

}
