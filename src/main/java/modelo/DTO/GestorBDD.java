package modelo.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import modelo.DAO.Producto;

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
		String INSERTPoduct = "INSERT INTO productos (id, codigo, nombre, cantidad, precio, caducidad, id_seccion) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement PSPoduct = super.cn.prepareStatement(INSERTPoduct);

		
		PSPoduct.setString(1, prod.getId());
		
	}
}