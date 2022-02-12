package RepasoBaseDatos.daos;


import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import RepasoBaseDatos.Conexion;
import RepasoBaseDatos.modelos.PersonaVO;

/*
 * Clase que permite el acceso a datos
 * Cuando utilizamos estas clases, aplicamos el patrón Data Access Object o DAO,
 * básicamente este parón consiste en centralizar los procesos de acceso a la base de datos
 * evitando inconsistencias y pisbles problemáticas cuando esto se realiza a lo largo de la aplicación
 * Con este patrón independizamos la lógica de negocio de la lógica de acceso a datos obteniendo mayor
 * organización y flexibilidad en el sistema.
 * 
 */
public class PersonaDAO {
	
	public void registrarPersona(PersonaVO miPersona) {
		Conexion conn = new Conexion();
		
		try {
			
			//El objeto estatuto statement me sirve para procesar
			//sentencias SQL y obtener los resultados de la misma 
			//consultar
			Statement estatuto = conn.getConnection().createStatement();
			
			//"INSERT INTO persona VALUES('"nombre"','"22"','"profesion"','"tel"')"
			estatuto.execute("INSERT INTO persona (nombre,edad,profesion,telefono) VALUES('"+miPersona.getNombrePersona()+
					"','"+miPersona.getEdadPersona()+"','"+miPersona.getProfesionPersona()+
					"','"+miPersona.getTelefonoPersona()+"')");
			
			
			JOptionPane.showMessageDialog(null, "Se ha registrado Existosamente a "+ miPersona.getNombrePersona(),"Información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close(); //Cierro el flujo y me desconecto de la base
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se registro a " + miPersona.getNombrePersona());
		} finally {
			try {
				conn.getConnection().close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		}
		  
		  
	}     
          
}



