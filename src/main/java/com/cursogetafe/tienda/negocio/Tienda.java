package com.cursogetafe.tienda.negocio;

import java.util.Set;

import com.cursogetafe.tienda.modelo.Producto;

public interface Tienda {
	
	/**
	 * Devuelve todos los productos ordenados por su descripción
	 * @return Un Set de Producto ordenado
	 */
	Set<Producto> getProductos();
	
	
	
	/**
	 * Devuelve todos los productos que contienen descripción ordenados por su descripción
	 * @param descripcion Descripcion de los productos a buscar
	 * @return Un Set de Producto ordenado
	 */
	Set<Producto> getProductos(String descripción);

}
