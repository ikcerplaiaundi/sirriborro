package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.Producto;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class InsertarProducto
 */
@WebServlet("/InsertarProducto")
public class InsertarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("InsertarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	usuario.setNombre(request.getParameter("nombre"));
		Producto   prod =new Producto();
		
		 prod.setId(Integer.parseInt(request.getParameter("id")));
		 prod.setCodigo(request.getParameter("codigo"));
		 prod.setNombre(request.getParameter("nombre"));
		 prod.setCantidad(Integer.parseInt(request.getParameter("cantidad"))  );
		 prod.setPrecio(Double.parseDouble(request.getParameter("precio")));
		 prod.setdate(request.getParameter("caducidad"));
		 prod.setId_seccion(Integer.parseInt(request.getParameter("id_seccion")));
		 GestorBDD GDBB=new GestorBDD();
			GDBB.abrirConexion();
			GDBB.insertProcucto(prod);
			GDBB.cerrarConexion();
		doGet(request, response);
	}

}
