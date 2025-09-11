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

let tabla;


function solicitud(){
	//Preparamos los parámetros para la petición Ej: idFabricante = 5
	let param = "idFabricante=" + encodeURIComponent(document.getElementById("idFabricante").value);
	
	//Creamos el objeto que nos permitirá hacer la petición
	let req = new XMLHttpRequest();
	
	//Indicamos el método HTTP y la URI
	req.open("post", "productos_fabricante_json_respuesta");
	
	//Registrar el oyente al evento readystatechange (Cambio de estado - cuando reciba respuesta)
	req.addEventListener("readystatechange", 
			function(){
		if(req.readyState == 4 && req.status == 200){
			cargarTabla(req);
		}
	});
	
	//Armar la cabecera
	req.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	
	//realizar una petición
	req.send(param);
}

function cargarTabla(req){
	
	let productos=JSON.parse(req.responseText);
	tabla.innerHTML="";
	for(let i = 0; i < productos.length; i++) {
		insertarFila(productos[i]);
	}
	document.querySelector("#tabla_datos").style.visibility = "visible";
}

function insertarFila(producto) {
	let tr = document.createElement("tr");
	let td = document.createElement("td");
	tr.appendChild(td);
	td.textContent = producto.producto;
	
	td = document.createElement("td");
	tr.appendChild(td);
	td.textContent = producto.precio;
	
	tabla.appendChild(tr);
	
}
	

window.onload = function() {
	document.getElementById("idFabricante").addEventListener("change",solicitud);
	tabla = document.querySelector("#tabla_datos tbody");
		
	}
	

</script>

<style type="text/css">
	#tabla_datos {
	visibility: hidden;
	}	
</style>

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
		
		
		<table id="tabla_datos" >
			<thead>
				<tr>
					<th>Descripcion</th>
					<th>Precio</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		
		<a href="${home}/menu_principal"><button>Volver</button></a>
	</div>
</body>
</html>