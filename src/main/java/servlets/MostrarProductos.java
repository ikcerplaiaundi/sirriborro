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
		if (idproducto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("idproducto", idproducto);
			response.sendRedirect("SelectMercado");
		}

		String idProductoEliminar = request.getParameter("eliminarespeial");
		if (idProductoEliminar != null) {

			GestorBDD GDBB = new GestorBDD();
			Producto producto = new Producto();
			producto.setId(Integer.parseInt(idProductoEliminar));

			GDBB.abrirConexion();
			producto = GDBB.SELECTPoducto(producto.getId());

			if (producto.getCantidad() == 0) {
				ArrayList<modelo.DAO.Mercado> mercadosProducto = GDBB.selectAllMercadosRelacionados(producto.getId());
				if (mercadosProducto.size() == 0) {
					GDBB.DELETEProducto(producto);
				} else {
					GDBB.DELETEMercadosRelacion(producto);
				}
			} else {
				GDBB.productoDisminulle(producto);
			}
			GDBB.cerrarConexion();

		}

		String idsProductosEliminar = request.getParameter("stringIdProductos");
		if (idsProductosEliminar != null) {
			String[] idlist = idsProductosEliminar.split(",");
			HttpSession session = request.getSession();

			// es numero?
			boolean numerico = true;
			for (String string : idlist) {
				try {
					Integer.parseInt(string);
				} catch (Exception e) {
					numerico = false;
					session.setAttribute("mensageStringIdProductos", "(" + string + ") no reconocible");
					e.addSuppressed(e);
				}
			}
			if (numerico) {

				GestorBDD GDBB = new GestorBDD();
				GDBB.abrirConexion();
				ArrayList<Producto> productos = GDBB.SELECTALLPoducto();
				GDBB.cerrarConexion();

				ArrayList<Producto> productosEliminar = new ArrayList<Producto>();

				boolean[] todoEncontrado = new boolean[idlist.length];
				for (boolean b : todoEncontrado) {
					b = false;
				}
				int i = 0;
				for (String idString : idlist) {
					for (Producto producto : productos) {
						if (producto.getId() == Integer.parseInt(idString)) {
							todoEncontrado[i] = true;
							productosEliminar.add(producto);
						}
					}
					i++;
				}

				boolean encontrado = true;
				for (int o = 0; i >= todoEncontrado.length; o++) {
					if (todoEncontrado[o] == false) {
						encontrado = todoEncontrado[o];
					}
				}

				if (encontrado) {
					for (Producto producto : productosEliminar) {
						GDBB.abrirConexion();
						GDBB.DELETEMercadosRelacion(producto);
						GDBB.DELETEProducto(producto);
						GDBB.cerrarConexion();
					}

				}else {
					session.setAttribute("mensageStringIdProductos", "multiples no encontrados");
				}

			}
		}

		doGet(request, response);

	}

}
