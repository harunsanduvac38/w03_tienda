<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login</title>
<link rel="stylesheet" type="text/css" href="${css}/alta_producto.css">
<script type="text/javascript">
	
	function validaForm(ev) {
		ev.preventDefault();
		let usr = document.getElementById("usr").value.trim();
		let pwd = document.getElementById("pwd").value.trim();
		let error = document.getElementById("error");
		
		if(!usr || !pwd) {
			error.textContent = "Todos los campos son obligatorios!";
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
		
		
		<p id="error">&nbsp;
			<c:if test="${error eq 'credenciales'}">Credenciales Incorrectas!</c:if>
			<c:if test="${error eq 'disabled'}">El usuario está bloqueado!</c:if>
		</p>
	</div>
</body>
</html>