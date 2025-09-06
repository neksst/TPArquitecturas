package org.practico.dao.interfaces;

import org.practico.entities.Factura;

public interface FacturaDAO {

	void insertFactura(int idFactura,int idCliente);
	Factura getFactura(int idFactura);
	
}
