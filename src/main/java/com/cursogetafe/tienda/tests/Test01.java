package com.cursogetafe.tienda.tests;

import com.cursogetafe.tienda.config.Config;
import com.cursogetafe.tienda.modelo.Fabricante;
import com.cursogetafe.tienda.modelo.Producto;

import jakarta.persistence.EntityManager;

public class Test01 {
	
	public static void main(String[] args) {
		
		
		EntityManager em = Config.getEmf().createEntityManager();
		
		
		Fabricante f = em.find(Fabricante.class, 1);
		
		Producto p = em.find(Producto.class, 1);
		
		System.out.println(f);
		System.out.println(p);
		System.out.println(f.getProductos());
		System.out.println(p.getFabricante());
		
		
		
		
	}

}
