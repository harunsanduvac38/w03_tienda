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
<link rel="stylesheet" type="text/css" href="${css}/alta_producto.css">
<script type="text/javascript">
	
	function validaForm(ev) {
		ev.preventDefault();
		let descripcion = document.getElementById("descripcion").value.trim();
		let precio = document.getElementById("precio").value.trim();
		let idFabricante = document.getElementById("idFabricante").value;
		let error = document.getElementById("error");
		if(!descripcion || !precio || !idFabricante) {
			error.textContent = "Todos los campos son obligatorios!";
		} else if(isNaN(precio)) {
			error.textContent = "El precio debe ser numérico!";
		} else if(precio <= 0) {
			error.textContent = "El precio debe ser mayor que 0";
		}else{
			error.textContent = "";
			event.currentTarget.submit();
		}
	}
	
	
	window.onload = function(){
		document.getElementById("form_login").addEventListener("submit", validaForm);
		
	}

</script>
</head>
<body>
	<header class="cabecera">
		<h2>Login</h2>
	</header>
	
	<div id="contPrincipal">
		<form id = "form_login" action="${home}/login" method="post">
			<input id="usr" type="text" name="usr" placeholder="Usuario">
			<input id="pwd" type="password" name="pwd" placeholder="Password">
			
			<button type="submit">Login</button>
		</form>
		
		
		<p id="error">&nbsp;</p>
	</div>
</body>
</html>