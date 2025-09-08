<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Productos</title>
<link rel="stylesheet" type="text/css" href="${css}/producto.css">
</head>
<body>
	<header class="cabecera">
		<h2>Busqueda de Productos</h2>
	</header>
	
	<div id="contPrincipal">
		<form action="${home}/listado_productos" method="post">
			<input type="search" name="descripcion" placeholder="producto">
			<button type="submit">Buscar</button>
		</form>
		
		<c:if test="${not empty prods}">
			<table id="tabla_datos" >
				<thead>
					<tr>
						<th>Descripcion</th>
						<th>Precio</th>
						<th>Fabricante</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="prod" items="${prods}">
						<tr>
							<td>${prod.producto}</td>
							<td>${prod.precio}</td>
							<td>${prod.fabricante.fabricante}</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</c:if>
		
		<a href="${home}/menu_principal"><button>Volver</button></a>
	</div>
</body>
</html>