package org.practico.dao.interfaces;

import java.util.List;
import org.practico.entities.Producto;

public interface ProductoDAO {
	
	void insertProducto(int idProducto,String nombre,int valor);
	Producto getProducto(int id);
	List<String> getProductos();
	String getProductoMasVendido();
}
