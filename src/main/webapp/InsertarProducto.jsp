<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



	<form action="InsertarProducto"  method="post">
	

		<label for="id">id</label> 
			<input type="text" name="id" id="" value=""><br> 
		<label for="id">codigo</label> 
			<input type="text" name="codigo" id="" value=""><br> 
		<label for="id">nombre</label> 
			<input type="text" name="nombre" id="" value=""><br> 
		<label for="id">cantidad</label> 
			<input type="text" name="cantidad" id="" value=""><br> 
		<label for="id">precio</label> 
			<input type="text" name="precio" id="" value=""><br> 
		<label for="id">caducidad</label> 
			<input type="text" name="caducidad" id="" value=""><br> 
		<label for="id">id_seccion</label> 
			<input type="text" name="id_seccion" id="" value=""><br> 
		
			
		<input type="submit" name="inset"value="inset" />
	</form>
</body>
</html>