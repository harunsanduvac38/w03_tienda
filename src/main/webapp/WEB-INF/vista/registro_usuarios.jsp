<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Registro Usuarios</title>
<link rel="stylesheet" type="text/css" href="${css}/alta_producto.css">
<script type="text/javascript">
	
	function validaForm(ev) {
		ev.preventDefault();
		let nombre = document.getElementById("nombre").value.trim();
		let usr = document.getElementById("usr").value.trim();
		let email = document.getElementById("email").value.trim();
		let pwd = document.getElementById("pwd").value.trim();
		let pwd2 = document.getElementById("pwd2").value.trim();
		let error = document.getElementById("error");
		
		if(!nombre || !usr || !email || !pwd || !pwd2) {
			error.textContent = "Todos los campos son obligatorios!";
		}else if (pwd != pwd2) {
			error.textContent = "Las contraseñas no coinciden!"
		} else if(!checkPwd(pwd)) {
			error.textContent = "La contraseña debe tener al menos 6 caracteres!";
		}else {
			error.textContent = "";
			event.currentTarget.submit();
		}
	}
	
	
	function checkPwd(pwd) {
		return pwd.length > 5;
	}
	
	
	window.onload = function(){
		document.getElementById("form_registro").addEventListener("submit", validaForm);
		
	}

</script>
</head>
<body>
	<header class="cabecera">
		<h2>Registro Usuarios</h2>
	</header>
	
	<div id="contPrincipal">
		<form id = "form_registro" action="${home}/registro_usuarios" method="post">
			<input id="nombre" type="text" name="nombre" placeholder="Nombre">
			<input id="usr" type="text" name="usr" placeholder="Usuario">
			<input id="email" type="email" name="email" placeholder="E-mail">
			<input id="pwd" type="password" name="pwd" placeholder="Password">
			<input id="pwd2" type="password" name="pwd2" placeholder="Repite Password">
			
			<button type="submit">Registrarse</button>
		</form>
		
		<p id="error">&nbsp;</p>
		
	</div>
</body>
</html>