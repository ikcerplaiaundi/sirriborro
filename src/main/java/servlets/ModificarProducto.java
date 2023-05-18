package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class ModificarProducto
 */
@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GestorBDD GDBB=new GestorBDD();
		GDBB.abrirConexion();
		ArrayList<modelo.DAO.Producto> productos = GDBB.SELECTALLPoducto();
		GDBB.cerrarConexion();
		GDBB.abrirConexion();
		ArrayList<modelo.DAO.Seccion> secciones = GDBB.SELECTALLSecciones();
		GDBB.cerrarConexion();
		if (secciones.size() == 0) {
			secciones = null;
		}
		request.setAttribute("secciones", secciones);
		// enviar datos
		
		request.setAttribute("productos", productos);
		// a que jsp?
		request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
