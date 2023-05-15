package modelo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Producto {

	private int id;
	private String codigo;
	private String nombre;
	private int cantidad;
	private double precio;
	private Date date;
	private int id_seccion;
	//2023-05-10
	private String pattern = "yyyy-MM-dd";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	
	
	public String toString() {
		return "Producto [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", cantidad=" + cantidad
				+ ", precio=" + precio + ", date=" + date + ", id_seccion=" + id_seccion + ", pattern=" + pattern
				+ ", simpleDateFormat=" + simpleDateFormat + "]";
	}

		// date
		public Date getdate() {
			return date;
		}

		public String getStringdate() {
			return "" + simpleDateFormat.format(this.date);
		}

		public String getStringdate(String patern) {
			this.setPattern(patern);
			return simpleDateFormat.format(this.date);
		}

		public void setdate(Date date) {
			this.date = date;
		}

		public void setdate(String date) {
			try {
				this.date = (Date) simpleDateFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		public String getPattern() {
			return pattern;

		}

		public void setPattern(String pattern) {
			this.pattern = pattern;
			String auxdate = getStringdate();
			this.simpleDateFormat = new SimpleDateFormat(pattern);
			this.setdate(auxdate);
		}

		public SimpleDateFormat getSimpleDateFormat() {
			return simpleDateFormat;
		}

		public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
			this.simpleDateFormat = simpleDateFormat;
		}

		//get set normal
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public int getId_seccion() {
			return id_seccion;
		}

		public void setId_seccion(int id_seccion) {
			this.id_seccion = id_seccion;
		}
		
		
}
