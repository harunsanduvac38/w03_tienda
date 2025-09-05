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
		
		<table id="tabla_datos" >
			<thead>
				<tr>
					<th>Descripcion</th>
					<th>Precio</th>
					<th>Fabricante</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Legion pro 5i</td>
					<td>1.999</td>
					<td>Lenovo</td>
				</tr>
				<tr>
					<td>Omen Max</td>
					<td>2.234</td>
					<td>HP</td>
				</tr>
				<tr>
					<td>Alienware g8</td>
					<td>1.567</td>
					<td>Dell</td>
				</tr>
				<tr>
					<td>Ordenador portatil</td>
					<td>1.124</td>
					<td>Sony</td>
				</tr>
				
			</tbody>
		</table>
		
		<a href="${home}/menu_principal"><button>Volver</button></a>
	</div>
</body>
</html>