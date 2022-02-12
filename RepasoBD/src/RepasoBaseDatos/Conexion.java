package RepasoBaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	//Nombre de la base de datos
	static String db = "protalento";
	
	static String user = "root";
	static String password = "";
	
	//String url ="jdbc:mysql://ip/nombrebasedatos";
	static String url ="jdbc:mysql://localhost/"+db;
	
	Connection conn = null;

	public Conexion() {
		//Obtener el driver para mysql
		
		//Se instancia con la ruta de nuestro build path
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Obtenemos la conexion
		conn = DriverManager.getConnection(url,user,password);
		if(conn != null) {
			System.out.println("Se conecto a la base de datos " + db);
		}
		}catch (SQLException e) {
			System.out.println("SQLException");
			e.printStackTrace();
			
		}
		/*
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
			e.printStackTrace();
		}
		*/
		catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Conexion miConexion = new Conexion();
	}

	
	//Metodo para obtener la conexion
	public Connection getConnection() {
		return conn;
	}
	
	

}
