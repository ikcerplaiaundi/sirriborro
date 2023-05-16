<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Insert title here</title>
</head>
<body>
	<h1>hola mundo</h1>


	<table>
	<c:forEach items="${productos}" var="producto">
		<tr>
			<th>${producto.getId() }</th>
			<th>${producto.getCodigo()}</th>
			<th>${producto.getNombre() }</th>
			<th>${producto.getCantidad()}</th>
			<th>${producto.getPrecio() }</th>
			<th>${producto.getDate()}</th>
			<th>${producto.getId_seccion()}</th>
			
		</tr>
	</c:forEach>
	</table>


</body>
</html>