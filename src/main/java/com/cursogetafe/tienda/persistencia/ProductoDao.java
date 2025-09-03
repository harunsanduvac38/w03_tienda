package com.cursogetafe.tienda.persistencia;

import java.util.List;

import com.cursogetafe.tienda.modelo.Producto;

public interface ProductoDao {

	
	/**
	 * Busca un Producto por su id.
	 * 
	 * @param idProducto
	 * @return Producto buscado, null si no existe.
	 */
	public Producto findById(int idProducto);
	
	/**
	 * Busca los productos cuyo nombre contenga la descripcion recibida.
	 * 
	 * @param descripcion a buscar
	 * @return Lista de productos que contienen esa descripcion
	 */
	public List<Producto> findByDescripcion(String descripcion);
	
	/**
	 * Retorna todos los productos registrados en la BBDD
	 * @return lista de productos
	 */
	public List<Producto> findAll();

	/**
	 * Opcional
	 * Agrega el producto recibido a la BBDD
	 * 
	 * @param p producto a almacenar
	 */
	public void save(Producto p);
}
