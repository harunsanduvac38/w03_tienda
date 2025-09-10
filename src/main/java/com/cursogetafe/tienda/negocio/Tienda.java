package com.cursogetafe.tienda.negocio;

import java.util.Set;

import com.cursogetafe.tienda.modelo.Fabricante;
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
	
	
	
	/**
	 * Devuelve todos los fabricantes ordenados por su nombre
	 * @return Un set de Fabricante ordenado por su nombre
	 */
	Set<Fabricante> getFabricantes();
	
	
	/**
	 * Retorna el fabricante buscado
	 * @param idFabricante id del fabricante
	 * @return el fabricante si existe, null si no existe
	 */
	
	Fabricante getFabricante(int idFabricante);
	
	
	
	/**
	 * Devuelve todos los fabricantes que proporcionan producto. Ordenados por su nombre.
	 * @return Un set de fabricantes ordenados
	 */
	Set<Fabricante> getFabricantesActivos();
	
	
	/**
	 * Agrega un nuevo producto en la persistencia
	 * @param p producto a agregar
	 */
	void crearProducto(Producto producto);
}
