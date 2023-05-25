package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import modelo.DAO.Mercado;
import modelo.DAO.Producto;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class SelectMecado
 */
@WebServlet("/SelectMercado")
public class SelectMercado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectMercado() {
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
		Producto producto = new Producto();
		HttpSession session = request.getSession();

		
		
		if (session.getAttribute("idproducto") != null) {

			producto.setId(Integer.parseInt((String) session.getAttribute("idproducto")));

			GDBB.abrirConexion();
			
			producto = GDBB.SELECTPoducto(producto.getId());
			
			ArrayList<modelo.DAO.Mercado> mercados = GDBB.SELECTALLMercados();
			
			ArrayList<modelo.DAO.Mercado> mercadosProducto = GDBB.selectAllMercadosRelacionados(producto.getId());
			GDBB.cerrarConexion();
			ArrayList<modelo.DAO.Mercado> mercadosSinProducto = new ArrayList<Mercado>();

			boolean coincidencia = true;
			for (Mercado mercado : mercados) {
				coincidencia = true;
				for (Mercado mercado2 : mercadosProducto) {
					if (mercado.getId() == mercado2.getId()) {
						coincidencia = false;
					}
				}
				if (coincidencia) {
					mercadosSinProducto.add(mercado);
				}
			}

			request.setAttribute("MercadosProducto", mercadosProducto);
			request.setAttribute("MercadosSinProducto", mercadosSinProducto);

		}

		request.setAttribute("producto", producto);

		request.getRequestDispatcher("SelectMercado.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		doGet(request, response);
		GestorBDD GDBB = new GestorBDD();
		String[] idString = request.getParameterValues("Mercados");
		GDBB.abrirConexion();
		for (String idmercado : idString) {
			GDBB.INSETproductos_supermercados(Integer.parseInt((String) session.getAttribute("idproducto")), idmercado);
		}
		GDBB.cerrarConexion();
		request.getRequestDispatcher("SelectMercado.jsp").forward(request, response);

	}

}
