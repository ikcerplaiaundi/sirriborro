package modelo.DTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import modelo.DAO.Producto;
import modelo.DAO.Seccion;

public class GestorBDD extends Conexion {
	

	public   ArrayList<modelo.DAO.Producto> SELECTALLPoducto () {
		String SELECTALLPoduct = "SELECT * FROM productos ";
		


		ArrayList<modelo.DAO.Producto> Productos = new ArrayList<modelo.DAO.Producto>();
		
		try {

			PreparedStatement SELECTALLProd = super.cn.prepareStatement(SELECTALLPoduct);
			ResultSet resultSet = SELECTALLProd.executeQuery(SELECTALLPoduct);
			
			while (resultSet.next()) {

				Producto Prod = new Producto();
				Prod.setId(resultSet.getInt("id"));
				Prod.setCodigo(resultSet.getString("codigo"));
				Prod.setNombre(resultSet.getString("nombre"));
				Prod.setCantidad(resultSet.getInt("cantidad"));
				Prod.setPrecio(resultSet.getDouble("precio"));
				Prod.setdate(resultSet.getString("caducidad"));
				Prod.setId_seccion(resultSet.getInt("id_seccion"));

				Productos.add(Prod);

			}
			

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Productos;
	}

	public void insertProcucto(Producto prod) {
		
		String INSERTPoduct = "INSERT INTO productos ( codigo, nombre, cantidad, precio, caducidad, id_seccion) VALUES ( ?, ?, ?, ?, ?, ?)";
		

		
		try {
			PreparedStatement PSPoduct = super.cn.prepareStatement(INSERTPoduct);
			

			
			
			
			PSPoduct.setString(1, prod.getCodigo());
			PSPoduct.setString(2, prod.getNombre());
			PSPoduct.setInt(3, prod.getCantidad());
			PSPoduct.setDouble(4, prod.getPrecio());
			PSPoduct.setDate(5, new Date(prod.getdate().getTime()));
			PSPoduct.setInt(6, prod.getId_seccion());
			
			PSPoduct.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public ArrayList<Seccion> SELECTALLSecciones() {
	String SELECTALLSeccion = "SELECT * FROM secciones ";
		


		ArrayList<modelo.DAO.Seccion> Seccions = new ArrayList<modelo.DAO.Seccion>();
		
		try {

			PreparedStatement SELECTALLProd = super.cn.prepareStatement(SELECTALLSeccion);
			ResultSet resultSet = SELECTALLProd.executeQuery(SELECTALLSeccion);
			
			while (resultSet.next()) {

				Seccion seccion = new Seccion();
				
				seccion.setId(resultSet.getInt("id"));
				seccion.setNombre(resultSet.getString("nombre"));

				Seccions.add(seccion);

			}
			

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Seccions;
		
	}

	public void updateProcucto(Producto prod) {
		//UPDATE productos SET codigo=?,nombre=?,cantidad=?,precio=?,caducidad=?,id_seccion=? WHERE id=?
		String updatePoduct = "UPDATE productos SET codigo=?,nombre=?,cantidad=?,precio=?,caducidad=?,id_seccion=? WHERE id=?";
		

		
		try {
			PreparedStatement PSPoduct = super.cn.prepareStatement(updatePoduct);
			
			PSPoduct.setString(1, prod.getCodigo());
			PSPoduct.setString(2, prod.getNombre());
			PSPoduct.setInt(3, prod.getCantidad());
			PSPoduct.setDouble(4, prod.getPrecio());
			PSPoduct.setDate(5, new Date( prod.getdate().getTime()));
			PSPoduct.setInt(6, prod.getId_seccion());
			PSPoduct.setInt(7, prod.getId());
			
			PSPoduct.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}