package com.cursogetafe.tienda.modelo;

import com.cursogetafe.tienda.config.Config;
import com.cursogetafe.tienda.persistencia.FabricanteDaoJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class TestMapeo {
	public static void main(String[] args) {
		
		EntityManager em = Config.getEmf().createEntityManager();
		
		Producto p = em.find(Producto.class, 5);
		System.out.println(p);
		System.out.println();
		
		
		String jpql = "select f from Fabricante f";
		TypedQuery<Fabricante> q = em.createQuery(jpql, Fabricante.class);
		
//		q.getResultList().forEach(System.out::println);
		
		for(Fabricante f : q.getResultList()) {
			System.out.println(f);
			for(Producto prod : f.getProductos()) {
				System.out.println("\t" + prod);
			}
		}
		
		
	}

}
