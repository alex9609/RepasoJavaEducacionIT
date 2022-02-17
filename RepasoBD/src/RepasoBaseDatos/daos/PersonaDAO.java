package RepasoBaseDatos.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

			// El objeto estatuto statement me sirve para procesar
			// sentencias SQL y obtener los resultados de la misma
			// consultar
			Statement estatuto = conn.getConnection().createStatement();

			// "INSERT INTO persona VALUES('"nombre"','"22"','"profesion"','"tel"')"
			estatuto.execute("INSERT INTO persona (nombre,edad,profesion,telefono) VALUES('"
					+ miPersona.getNombrePersona() + "','" + miPersona.getEdadPersona() + "','"
					+ miPersona.getProfesionPersona() + "','" + miPersona.getTelefonoPersona() + "')");

			JOptionPane.showMessageDialog(null, "Se ha registrado Existosamente a " + miPersona.getNombrePersona(),
					"Información", JOptionPane.INFORMATION_MESSAGE);
			estatuto.close(); // Cierro el flujo y me desconecto de la base

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se registro a " + miPersona.getNombrePersona());
		} finally {
			try {
				conn.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}

		}

	}

	public PersonaVO buscarPersona(int id) {
		PersonaVO rp = null;
		int number_id;
		Conexion conn = new Conexion();

		if (existePersona(id)) {
			try {
				Statement estatuto = conn.getConnection().createStatement();
				estatuto.execute("SELECT * FROM persona WHERE id = " + id);
				ResultSet datos = estatuto.getResultSet();

				if (datos.next()) {
					number_id = (int) datos.getLong(1);
					String nombre = datos.getString(2);
					int edad = (int) datos.getLong(3);
					String profesion = datos.getString(4);
					int telefono = (int) datos.getLong(5);
					rp = new PersonaVO(nombre, edad, profesion, telefono);
					rp.setIdPersona(number_id);

				}
				estatuto.close();

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se pudo obtener datos " + e.getMessage());
			} finally {
				conn.cerrar();
			}
		}

		return rp;

	}

	public void eliminarPersona(int id) {
		Conexion conn = new Conexion();
		if (existePersona(id)) {
			try {
				Statement estatuto = conn.getConnection().createStatement();
				estatuto.execute("DELETE FROM persona WHERE ID = " + id);
				JOptionPane.showMessageDialog(null, "Se ha eliminado con exito la persona");
				estatuto.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn.cerrar();
			}
		} else {
			JOptionPane.showMessageDialog(null, "No existe la persona con el id " + id);
		}

	}
	
	public void actualizarPersona(int id, PersonaVO persona) {
		Conexion conn = new Conexion();
			try {
				String sql = "UPDATE persona SET nombre = ?, edad = ?, profesion = ?, telefono = ? WHERE id = " + id;
				PreparedStatement estatuto = conn.getConnection().prepareStatement(sql);
				estatuto.setString(1, persona.getNombrePersona());
				estatuto.setLong(2, persona.getEdadPersona());
				estatuto.setString(3, persona.getProfesionPersona());
				estatuto.setLong(4, persona.getTelefonoPersona());
				estatuto.execute();
				
				JOptionPane.showMessageDialog(null, "Se ha realizado la actulización con exito");
				
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Error al realizar en la actualización" + e.getMessage());
				JOptionPane.showMessageDialog(null, "No se pudo hacer la actualización ");
			}	
	}

	public boolean existePersona(int id) {
		Conexion conn = new Conexion();
		boolean encontrado = false;
		Statement estatuto = null;
		try {
			estatuto = conn.getConnection().createStatement();
			estatuto.execute("SELECT * FROM persona WHERE id = " + id);
			ResultSet datos = estatuto.getResultSet();
			encontrado = datos.next();
			/*
			 * if(datos.next()) { encontrado = true; }
			 */

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al realizar la busqueda" + e.getMessage());
			JOptionPane.showMessageDialog(null, "No se pudo hacer la consulta ");
			return false;
		} finally {
			conn.cerrar();
			try {
				estatuto.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encontrado;
	}

}
