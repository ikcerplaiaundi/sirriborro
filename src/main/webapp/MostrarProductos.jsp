<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Insert title here</title>
</head>
<body>
	
	<form action="MostrarProductos" method="get">
		<input type="text" name="filtro" id="" value="${filtro}">
		<input type="submit" name="buscar" value="buscar" /><br>
		<label>precioMin</label>
		<input type="text" name="precioMin" id="" value="${precioMin}">
		<label>precioMax</label>
		<input type="text" name="precioMax" id="" value="${precioMax}">
		<input type="submit" name="submit" value="codigo_asc" />
		<input type="submit" name="submit" value="codigo_desc" />
		</form>

	<table>
		<tr>
			<td>Id()</td>
			<td>Codigo</td>
			<td>Nombre</td>
			<td>Cantidad()</td>
			<td>Precio</td>
			<td>caducidad</td>
			<td>Id_seccion</td> 
			
		</tr>
	<c:forEach items="${filtrados}" var="producto">
		<tr>
			<td>${producto.getId() }</td>
			<td>${producto.getCodigo()}</td>
			<td>${producto.getNombre() }</td>
			<td>${producto.getCantidad()}</td>
			<td>${producto.getPrecio() }</td>
			<td>${producto.getdate()}</td>
			<td>${producto.getId_seccion()}</td> 
			
		</tr>
	</c:forEach>
	
	</table>


<a href="InsertarProducto">insertar nuevo</a>
<a href="ModificarProducto">MODIFICAR</a>
</body>
</html>