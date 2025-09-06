package org.practico.dao.interfaces;

import java.util.List;
import org.practico.entities.Producto;

public interface ProductoDAO {
	Producto getProducto(int id);
	List<String> getProductos();
	Producto getProductoMasVendido();
}
