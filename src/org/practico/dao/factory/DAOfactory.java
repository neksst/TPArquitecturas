package org.practico.dao.factory;

import org.practico.dao.interfaces.ClienteDAO;
import org.practico.dao.interfaces.FacturaDAO;
import org.practico.dao.interfaces.FacturaProductoDAO;
import org.practico.dao.interfaces.ProductoDAO;

public abstract class DAOfactory {

	public final static int MYSQL = 1;
	//public static int DERBY = 2;

	private static DAOfactory INSTANCE = null;

	public abstract ClienteDAO  getCLienteDAO();
	public abstract ProductoDAO getProductoDAO();
	public abstract FacturaDAO  getFacturaDAO();
	public abstract FacturaProductoDAO getFacturaProductoDAO();
	
	public abstract void loadDB();

	public static DAOfactory getDAOfactory(int DB) {
		if (INSTANCE == null) {
			switch (DB) {
			case MYSQL:
				INSTANCE =  new MYSQLDAOFactory(); break;
			/*case DERBY:
				INSTANCE = new DERBYDAOFactory();*/
			default:
				throw new IllegalArgumentException("Tipo de factory no soportado");
			}
		}

		return INSTANCE;
	}

	public static DAOfactory getInstance() {
		return INSTANCE;
	}

}
