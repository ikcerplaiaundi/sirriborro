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
        <c:if test="${mensageError!=null}">
			<h2>${mensageError}</h2>
		</c:if>


		<c:forEach items="${productos}" var="producto">
		<form action="ModificarProducto" method="post">
		
		<label for="id">id</label> 
		<input type="number" name="id" id=""value="${producto.getId()}">
		<label for="id">codigo</label> 
		<input type="text" name="codigo" id=""value="${producto.getCodigo()}">
		<label for="id">nombre</label> 
		<input type="text" name="nombre" id="" value="${producto.getNombre() }">
		<label for="id">cantidad</label> 
		<input type="text" name="cantidad" id="" value="${producto.getCantidad()}">
		<label for="id">precio</label> 
		<input type="text" name="precio" id="" value="${producto.getPrecio() }">
		<label for="id">caducidad</label> 
		<input type="text" name="caducidad" id="" value="${producto.date}">
		<label for="seccionnames">Choose:</label> 
		<select name="id_seccion" id="seccionnames">
		
			<option value="${producto.getId_seccion()}">mantener</option>
		
			<c:forEach items="${secciones}" var="seccion">
				<option  value="${seccion.getId() }">${seccion.getNombre()}</option>
			</c:forEach>
		</select> 
		<input type="submit" name="update" value="update" />
		
		</form>


		</c:forEach>

</body>
</html>