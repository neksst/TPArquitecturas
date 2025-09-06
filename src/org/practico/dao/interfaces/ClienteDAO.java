package org.practico.dao.interfaces;

import java.util.List;

import org.practico.entities.Cliente;

public interface ClienteDAO {
	void insertCliente(int id, String Nombre,String Email); 
	List<String> getClientes();
	Cliente getCliente(int id);
	List<String> getClientesMasFacturados();
	 
}
