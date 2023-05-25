<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
		<tr>
			<td>${producto.getId() }</td>
			<td>${producto.getCodigo()}</td>
			<td>${producto.getNombre() }</td>
			<td>${producto.getCantidad()}</td>
			<td>${producto.getPrecio() }</td>
			<td>${producto.getdate()}</td>
			<td>${producto.getId_seccion()}</td>
</table>
<form action="SelectMercado" method="post">
	<table>
			<tr>
				<td>Id()</td>
				<td>Nombre</td>
			</tr>
		<c:forEach items="${MercadosSinProducto}" var="Mercado">
		
			<tr>
				<td>${Mercado.getId() }</td>
			
				<td>${Mercado.getNombre() }</td>
			
				<td><input type="checkbox" name="Mercados" value="${Mercado.getId()}"></td>
			
			</tr>
			
		</c:forEach>
		
	</table>
	<input type="submit" name="mercados a asignar" value="submit" />
</form>
</body>
</html>