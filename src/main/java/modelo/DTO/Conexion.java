package modelo.DTO;

import java.sql.*;

public class Conexion {
	protected Connection cn;
	
	public Conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCn() {
		return cn;
	}

	public void setCn(Connection cn) {
		this.cn = cn;
	}
	
	public void abrirConexion() {
		try {
			
			String urlconexion = "jdbc:mysql://localhost/Productos";
			cn = (Connection) DriverManager.getConnection(urlconexion, "root", "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() {
		try {
			
			cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

