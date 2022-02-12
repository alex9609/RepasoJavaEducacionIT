package RepasoBaseDatos.programa;

import javax.swing.JOptionPane;

import RepasoBaseDatos.daos.PersonaDAO;
import RepasoBaseDatos.modelos.PersonaVO;

public class AppPrincipal {

	public static void main(String[] args) {
		
		PersonaVO p1 = new PersonaVO("Alex",22,"Developer",316743);
		
		PersonaDAO pdao = new PersonaDAO();
		
		//pdao.registrarPersona(p1);
		int opcion;
		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion deseada"+
					"\n 1. Crear persona \n 2. Borrar persona "));
			switch(opcion) {
			case 1:
				JOptionPane.showInputDialog("Ingrese a la opcion 1");
				//Pedir los datos para cargar un objeto persona y luego persistirlo
				break;
			case 2:
				JOptionPane.showInputDialog("Ingrese a la opcion 2");
				//Pedir un id para borrar un objeto persona (Validar que dicho id exista en la db)
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Gracias");
				break;
			}
			
		}while(opcion != 1 && opcion != 2 && opcion != 3);
		
	}

}
