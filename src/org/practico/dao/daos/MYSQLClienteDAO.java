package org.practico.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
	
	@Override
	public List<String> getClientesMasFacturados(){
		List<String> l = new LinkedList<String>();
		String join = "SELECT c.Nombre AS Cliente, p.Nombre AS Producto, fp.Cantidad, p.valor, fp.Cantidad * p.valor AS TOTAL FROM Cliente c JOIN Factura f ON c.idCliente = f.idCliente JOIN factura_producto fp ON f.idFactura = fp.idFactura JOIN producto p ON p.idProducto = fp.idProducto GROUP BY c.Nombre ORDER BY TOTAL DESC;";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(join);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l.add("Cliente: " + rs.getString(1) + " Producto: " + rs.getString(2) + " Cantidad: " + rs.getInt(3) + " Valo: " + rs.getInt(4) + " Total: " + rs.getInt(5));
			}
			ps.close();
			return l;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

}
