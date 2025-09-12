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

</head>
<body>
	<header class="cabecera">
		<h2>Registro Usuarios</h2>
	</header>
	
	<div id="contPrincipal">
		<c:choose>
			<c:when test="${resu eq 'ok'}">
				<p>El usuario <span id="usr">${nombreUsuario}</span> se ha registrado correctamente!</p>
			</c:when>
			<c:when test="${resu eq 'existe'}">
				<p>El usuario <span id="usr">${nombreUsuario}</span> ya existe!</p>
				<a href="${home}/registro_usuarios"><button>Registro Usuarios</button></a>
			</c:when>
			<c:when test="${resu eq 'error'}">
				<p>ERROR al crear el Usuario <span id="usr">${nombreUsuario}</span>, inténtelo nuevamente!</p>
				<a href="${home}/registro_usuarios"><button>Registro Usuarios</button></a>
			</c:when>
		</c:choose>
		<a href="${home}/login"><button>Login</button></a>
	</div>
</body>
</html>