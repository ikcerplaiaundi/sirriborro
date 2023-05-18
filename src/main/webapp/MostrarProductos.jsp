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
	<h1>hola mundo</h1>


	<table>
	<c:forEach items="${productos}" var="producto">
		<tr>
			<td>${producto.getId() }</td>
			<td>${producto.getCodigo()}</td>
			<td>${producto.getNombre() }</td>
			<td>${producto.getCantidad()}</td>
			<td>${producto.getPrecio() }</td>
			<td>${producto.getDate()}</td>
			<td>${producto.getId_seccion()}</td>
			<a href="ModificarProducto?=${producto.getId()}">MODIFICAR</a>
		</tr>
	</c:forEach>
	</table>


<a href="InsertarProducto">insertar nuevo</a>

</body>
</html>