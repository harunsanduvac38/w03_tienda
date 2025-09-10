<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Productos</title>
<link rel="stylesheet" type="text/css" href="${css}/producto.css">

<script type="text/javascript">

	window.onload = function() {
		document.getElementById("idFabricante").addEventListener("change", 
				function(){
			this.form.submit();
		}
	);
	}



</script>
</head>
<body>
	<header class="cabecera">
		<h2>Busqueda de Productos por Fabricante</h2>
	</header>
	
	<div id="contPrincipal">
		<form action="${home}/productos_fabricante" method="post">
			<select id="idFabricante" name="idFabricante">
				<option hidden="hidden" value="">Seleccione Fabricante</option>
				<c:forEach var = "fabricante" items="${fabs}">
					<option value="${fabricante.idFabricante}">${fabricante.fabricante}</option>
				</c:forEach>
			</select>
		</form>
		
		<c:if test="${not empty fabs}">
			<table id="tabla_datos" >
				<thead>
					<tr>
						<th>Descripcion</th>
						<th>Precio</th>
						<th>Fabricante</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="prod" items="${fab.productos}">
						<tr>
							<td>${prod.producto}</td>
							<td>${prod.precio}</td>
							
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</c:if>
		
		<a href="${home}/menu_principal"><button>Volver</button></a>
	</div>
</body>
</html>