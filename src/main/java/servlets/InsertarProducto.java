package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.ConcurrentDateFormat;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GestorBDD GDBB = new GestorBDD();
		GDBB.abrirConexion();
		ArrayList<modelo.DAO.Seccion> secciones = GDBB.SELECTALLSecciones();
		GDBB.cerrarConexion();
		if (secciones.size() == 0) {
			secciones = null;
		}
		request.setAttribute("secciones", secciones);
		request.getRequestDispatcher("InsertarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Producto prod = new Producto();
		GestorBDD GDBB = new GestorBDD();
		
		boolean check[] = new boolean[7];
		for (boolean b : check) {
			b = false;
		}
		
		GDBB.abrirConexion();
		ArrayList<modelo.DAO.Producto> productos = GDBB.SELECTALLPoducto();
		GDBB.cerrarConexion();

		
		prod.setId(1);
		check[0] = true;
		

		codigoRepetdo(request, prod, check, productos);

		if (request.getParameter("nombre") != null) {
			prod.setNombre(request.getParameter("nombre"));
			check[2] = true;
		}

		candidaPositivo(request, prod, check);
		precioPositivo(request, prod, check);
		noPastDate(request, prod, check);
		sectionRequired(request, prod, check);
		System.out.println(check.toString().contains("false"));
		
		
		boolean todobien=true;
		for(boolean comprobacion :check) {
			if(!comprobacion) {todobien=false;}
		}
		if (todobien) {
			GDBB.abrirConexion();
			GDBB.insertProcucto(prod);
			GDBB.cerrarConexion();
		}

		doGet(request, response);
	}

	private void sectionRequired(HttpServletRequest request, Producto prod, boolean[] check) {
		if (request.getParameter("id_seccion") != null) {
			prod.setId_seccion(Integer.parseInt(request.getParameter("id_seccion")));
			if (prod.getId_seccion() == 0) {
				request.setAttribute("mensageError", "seccion necesaria");
			} else {
				check[6] = true;
			}
		}
	}
	
	private void noPastDate(HttpServletRequest request, Producto prod, boolean[] check) {
		
		try {
			System.out.println("caducidad: "+request.getParameter("caducidad"));
			if ((request.getParameter("caducidad") != null)) {
				if(((Date) prod.getSimpleDateFormat().parse(request.getParameter("caducidad"))).before(new Date())){
					
					request.setAttribute("mensageError", "fecha futura requerida");
				}	
				else {
					prod.setdate((Date) prod.getSimpleDateFormat().parse(request.getParameter("caducidad")));
					check[5] = true;
			 	}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void precioPositivo(HttpServletRequest request, Producto prod, boolean[] check) {
		if (request.getParameter("precio") != null) {
			prod.setPrecio(Double.parseDouble(request.getParameter("precio")));
			if (prod.getPrecio() >= 0) {
				check[4] = true;
			}
		}
	}

	private void candidaPositivo(HttpServletRequest request, Producto prod, boolean[] check) {
		if (request.getParameter("cantidad") != null) {
			prod.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
			if (prod.getCantidad() >= 0) {
				check[3] = true;
			}
		}
	}

	private void codigoRepetdo(HttpServletRequest request, Producto prod, boolean[] check,
			ArrayList<modelo.DAO.Producto> productos) {
		if (request.getParameter("codigo") != null) {
			prod.setCodigo(request.getParameter("codigo"));
			for (Producto producto : productos) {
				if (producto.getCodigo().equals(prod.getCodigo())) {
					request.setAttribute("mensageError", "codigo repetido");

				} else {
					check[1] = true;
				}
			}
		}
	}
}
