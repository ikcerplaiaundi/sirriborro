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

	<h2>${mensageError}</h2>

	<form action="InsertarProducto" method="post">


		<label for="id">id</label> <input type="text" name="id" id="" value=""><br>
		<label for="id">codigo</label> <input type="text" name="codigo" id=""
			value=""><br> <label for="id">nombre</label> 
			<input
			type="text" name="nombre" id="" value=""><br> 
			<label
			for="id">cantidad</label> 
			<input type="text" name="cantidad" id=""
			value=""><br>
			 <label for="id">precio</label> 
			 <input type="text" name="precio" id="" value=""><br> <label
			for="id">caducidad</label> 
			<input type="date" name="caducidad" id=""
			value=""><br> 
			<label for="seccionnames">Choose:</label> <select
			name="id_seccion" id="seccionnames">
			<option value="0">choose</option>
			<c:forEach items="${secciones}" var="seccion">
				<option value="${seccion.getId() }">${seccion.getNombre()}</option>
			</c:forEach>
		</select> <input type="submit" name="inset" value="inset" />
	</form>







</body>
</html>