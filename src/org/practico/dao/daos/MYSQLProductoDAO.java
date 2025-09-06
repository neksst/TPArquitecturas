package org.practico.dao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.practico.dao.interfaces.ProductoDAO;
import org.practico.entities.Producto;

public class MYSQLProductoDAO implements ProductoDAO{

	private Connection con;
	
	public MYSQLProductoDAO(Connection con) {
		this.con = con;
	}
	
	
	@Override
	public void insertProducto(int idProducto,String nombre,int valor) {
		String insert = "INSERT INTO Producto (idProducto,nombre,valor) VALUES (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setInt(1, idProducto);
			ps.setString(2, nombre);
			ps.setInt(3, valor);
			ps.executeUpdate();
			ps.close();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Producto getProducto(int id) {
		Producto c = new Producto();
		String select = "SELECT * FROM PRODUCTO WHERE idProducto = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c.setIdProducto(id);
				c.setNombre(rs.getString(2));
				c.setValor(rs.getInt(3));
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
	public List<String> getProductos() {
		List<String> l = new LinkedList<String>();
		String select = "SELECT * FROM PRODUCTO";
		PreparedStatement ps;
		try{
			ps = con.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l.add("Nombre: " + rs.getString(2) + " Valor: " + rs.getInt(3));
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public String getProductoMasVendido() {
		String res = "No encontrado!";
		String join = "SELECT p.Nombre, fp.Cantidad, valor , fp.Cantidad*p.valor TOTAL FROM producto p JOIN factura_producto fp ON p.idProducto = fp.idProducto GROUP BY p.Nombre ORDER BY 4 DESC LIMIT 1;";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(join);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = "Nombre: " + rs.getString(1) + " Cantidad: " + rs.getInt(2) + " Valor: " + rs.getInt(3) + " TOTAL: " + rs.getInt(4);
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
				
	}
	
	

}
