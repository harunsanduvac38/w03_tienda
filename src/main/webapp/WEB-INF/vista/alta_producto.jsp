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
<link rel="stylesheet" type="text/css" href="${css}/alta_producto.css">
<script type="text/javascript">
	
	function validaForm() {
		
	}
	
	
	window.onload = function(){
		document.getElementById("form_prod").addEventListener("submit", validaForm);
		
	}

</script>
</head>
<body>
	<header class="cabecera">
		<h2>Alta de Producto</h2>
	</header>
	
	<div id="contPrincipal">
		<form id = "form_prod" action="${home}/alta_producto" method="post">
			<input type="text" name="descripcion" placeholder="Descripcion">
			<input type="text" name="precio" placeholder="Precio">
			<select name = "idFabricante">
				<option value="20">Asus20</option>
				<option value="19">Asus19</option>
				<option value="18">Asus18</option>
				<option value="17">Asus17</option>
				
			</select>
			<button type="submit">Crear</button>
		</form>
		
		
		
		<a href="${home}/menu_principal"><button>Volver</button></a>
	</div>
</body>
</html>