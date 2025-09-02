package com.cursogetafe.tienda.tests;

import java.awt.geom.QuadCurve2D;

import com.cursogetafe.tienda.config.Config;
import com.cursogetafe.tienda.modelo.Fabricante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Test02 {
	public static void main(String[] args) {
		
	
	
	EntityManager em = Config.getEmf().createEntityManager();
	
	
	String jpql = "select f from Fabricante f order by f asc";
	
	TypedQuery<Fabricante> q = em.createQuery(jpql, Fabricante.class);
	
	q.getResultList().forEach(System.out::println);
	System.out.println();
	
	
	jpql = "select f.fabricante, count(p) from Fabricante f join f.productos p group by f.fabricante";
	
	TypedQuery<Object[]> q2 = em.createQuery(jpql, Object[].class);
	
	for(Object[] ob : q2.getResultList()) {
		System.out.println(ob[0] + ": " + ob[1] + " Productos");
	}
	
	}
}
