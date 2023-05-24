package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Producto;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class MostrarProductos
 */
@WebServlet("/MostrarProductos")
public class MostrarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarProductos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GestorBDD GDBB = new GestorBDD();
		GDBB.abrirConexion();
		ArrayList<Producto> productos = GDBB.SELECTALLPoducto();
		GDBB.cerrarConexion();

		ArrayList<Producto> filtrados = new <Producto>ArrayList();
		filtros(request, productos, filtrados);

		// enviar datos
		request.setAttribute("filtrados", filtrados);
		// a que jsp?
		request.getRequestDispatcher("MostrarProductos.jsp").forward(request, response);

	}

	private void filtros(HttpServletRequest request, ArrayList<Producto> productos, ArrayList<Producto> filtrados) {
		String filtro = request.getParameter("filtro");
		float precioMin = 0;
		float precioMax = Float.MAX_VALUE;

		String submit = request.getParameter("submit");

		if (request.getParameter("precioMin") != null) {
			precioMin = Float.parseFloat(request.getParameter("precioMin"));
		}
		if (request.getParameter("precioMax") != null) {
			precioMax = Float.parseFloat(request.getParameter("precioMax"));
		}

		if (filtro == null) {
			filtro = "";
		}
		if (submit == null) {
			submit = "";
		}

		for (Producto producto : productos) {
			if (((producto.getCodigo().contains(filtro))) || ((producto.getNombre().contains(filtro)))) {
				if (producto.getPrecio() < precioMax && producto.getPrecio() > precioMin) {
					filtrados.add(producto);
				}

			}
		}

		if (submit.equals("codigo_asc")) {
			filtrados.sort((p1, p2) -> p1.getCodigo().compareTo(p2.getCodigo()));
		}

		if (submit.equals("codigo_desc")) {
			filtrados.sort((p1, p2) -> p2.getCodigo().compareTo(p1.getCodigo()));
		}
		request.setAttribute("precioMin", precioMin);
		request.setAttribute("precioMax", precioMax);
		request.setAttribute("filtro", filtro);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idproducto = request.getParameter("supermercado");
		if(idproducto!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("idproducto", idproducto);
			response.sendRedirect("SelectMercado");
		}else {doGet(request, response);}
		
	}

}
