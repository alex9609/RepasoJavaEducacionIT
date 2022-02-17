package RepasoBaseDatos.programa;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import RepasoBaseDatos.daos.PersonaDAO;
import RepasoBaseDatos.modelos.PersonaVO;

public class AppPrincipal {

	public static void main(String[] args) {
		PersonaDAO pdao = new PersonaDAO();
		/*
		 * PersonaVO p1 = new PersonaVO("Alex",22,"Developer",316743);
		 * 
		 * PersonaVO p1 = new PersonaVO("Camilo",25,"Marketing",356743);
		 * pdao.actualizarPersona(8, p1);
		 * 
		 * pdao.eliminarPersona(2);
		 * 
		 * //pdao.registrarPersona(p1);
		 */

		int opcion;
		do {
			try {
				opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion deseada"
						+ "\n 1. Crear persona \n 2. Borrar persona  \n 3. Consultar persona \n 4. Actualizar persona \n 5. Salir"));
			} catch (NumberFormatException e) {
				String msj = e.getMessage();
				if (msj.equals("null")) {
					opcion = 4;
				} else {
					opcion = 6;
					JOptionPane.showMessageDialog(null, "Ingrese un valor entero");
				}
			}

			switch (opcion) {
			case 1:

				// Validar los datos
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona");
				int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad de " + nombre));
				String profesion = JOptionPane.showInputDialog("Ingrese la profesion de " + nombre);
				int telefono = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el telefono " + nombre));

				PersonaVO p1 = new PersonaVO(nombre, edad, profesion, telefono);
				pdao.registrarPersona(p1);

				break;
			case 2:
				try {
					int id = Integer
							.parseInt(JOptionPane.showInputDialog("Ingrese le id a eliminar en la base de datos"));
					pdao.eliminarPersona(id);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Ingrese un dato entero");

				}
				// Pedir un id para borrar un objeto persona (Validar que dicho id exista en la
				// db)
				break;
			case 3:
				try {
					int id1 = Integer
							.parseInt(JOptionPane.showInputDialog("Ingrese le id a consultar en la base de datos"));
					PersonaVO people = pdao.buscarPersona(id1);
					if (people != null) {
						System.out.println(people.toString());
					} else {
						JOptionPane.showMessageDialog(null, "No existe la persona con el id " + id1);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Ingrese un dato entero");
				}
				break;
			case 4:
				int id1 = Integer
						.parseInt(JOptionPane.showInputDialog("Ingrese le id a consultar en la base de datos"));
				if (pdao.existePersona(id1)) {
					String nombre_nuevo = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la persona");
					int edad_nueva = Integer
							.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad de " + nombre_nuevo));
					String profesion_nueva = JOptionPane
							.showInputDialog("Ingrese la nueva profesion de " + nombre_nuevo);
					int telefono_nuevo = Integer
							.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo telefono " + nombre_nuevo));

					PersonaVO person = new PersonaVO(nombre_nuevo, edad_nueva, profesion_nueva, telefono_nuevo);
					pdao.actualizarPersona(id1, person);
				} else {
					JOptionPane.showMessageDialog(null, "No existe la persona con el id " + id1);
				}
				break;

			case 5:
				JOptionPane.showMessageDialog(null, "Gracias");
				break;
			}

		} while (opcion != 5);

	}

//  Controlar lo que le pasamos por parametro a persona
}
