import org.practico.dao.factory.DAOfactory;
import org.practico.dao.interfaces.ClienteDAO;

public class Servicio {

	public static void main(String[] args) {
		DAOfactory mysqlDAO = DAOfactory.getDAOfactory(1);
		mysqlDAO.connect();
		//mysqlDAO.loadDB();
		ClienteDAO ClienteDAO = mysqlDAO.getCLienteDAO();
		ClienteDAO.insertCliente(1, "Agus", "agus@mail.com");
		System.out.println(ClienteDAO.getCliente(1));
	}

}
