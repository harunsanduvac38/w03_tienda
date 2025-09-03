package com.cursogetafe.tienda.persistencia;

import java.util.Set;

import com.cursogetafe.tienda.modelo.Fabricante;


public interface FabricanteDao {
	
	/**
	 * Agrega un nuevo fabricante a la BBDD si no existe y si existe lo modifica
	 * @param fabricante a agregar
	 */
	public void save(Fabricante fabricante);
	
	/**
	 * Busca un fabricante por su id. La lista de productos no debe ser cargada (Lazy)
	 * 
	 * @param idFabricante del Fabricante buscado
	 * @return Fabricante o null si no existe
	 */
	public Fabricante findByIdLazy(int idFabricante);
	
	/**
	 * Busca un fabricante por su id. Debe retornarse con su lista de productos cargada
	 * 
	 * @param idFabricante del Fabricante buscado
	 * @return Fabricante o null si no existe
	 */
	public Fabricante findById(int idFabricante);
	
	/**
	 * Debe seleccionar solo aquellos fabricantes que proporcionan algun producto
	 * La lista de productos no se utilizara, asi que es necesario que tenga un comportamiento Lazy
	 * 
	 * @return Set con los fabricantes que proporcionan algun producto
	 */
	public Set<Fabricante> findOnlyActive();
	
	/**
	 * Opcional
	 * Retorna todos los fabricantes que proporcionan algun producto
	 * La lista de productos no se utilizara, asi que es necesario que tenga un comportamiento Lazy
	 * 
	 * @return Set con los fabricantes
	 */
	public Set<Fabricante> findAll();
	
}
