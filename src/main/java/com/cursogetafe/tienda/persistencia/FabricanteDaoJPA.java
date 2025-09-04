package com.cursogetafe.tienda.persistencia;

import java.util.HashSet;
import java.util.Set;

import com.cursogetafe.tienda.config.Config;
import com.cursogetafe.tienda.modelo.Fabricante;
import com.cursogetafe.tienda.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class FabricanteDaoJPA implements FabricanteDao {
	
	EntityManagerFactory emf;
	EntityManager em;
	
	public FabricanteDaoJPA() {
		emf = Config.getEmf();
	}
	
	public FabricanteDaoJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	

	@Override
	public void save(Fabricante fabricante) {
		em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(fabricante);
			em.getTransaction().commit();	
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		em.close();
			
		
			
		
	}

	@Override
	public Fabricante findByIdLazy(int idFabricante) {
		em = emf.createEntityManager();
		
		String jpql = "select f from Fabricante f where f.idFabricante = :id";
		TypedQuery<Fabricante> q = em.createQuery(jpql, Fabricante.class);
		q.setParameter("id", idFabricante);
		
		Fabricante buscado = q.getSingleResultOrNull();
		
		em.close();
		return buscado;
	}

	@Override
	public Fabricante findById(int idFabricante) {
		em = emf.createEntityManager();
		
		String jpql = "select f from Fabricante f left join fetch f.productos where f.idFabricante = :id";
		
		TypedQuery<Fabricante> q = em.createQuery(jpql, Fabricante.class);
		q.setParameter("id", idFabricante);
		Fabricante buscado = q.getSingleResultOrNull();
		for(Producto prod : buscado.getProductos()) {
			System.out.println(prod);
		}
		
		em.close();
		return buscado;
	}

	
	@Override
	public Set<Fabricante> findOnlyActive() {
		em = emf.createEntityManager();
		
		String jpql = "select distinct f from Fabricante f join f.productos p";
		
		TypedQuery<Fabricante> q = em.createQuery(jpql, Fabricante.class);
		Set<Fabricante> set = new HashSet<Fabricante>(q.getResultList());
		
		em.close();
		return set;
		
		
	}

	@Override
	public Set<Fabricante> findAll() {
		em = emf.createEntityManager();
		
		String jpql = "select distinct f from Fabricante f";
		
		TypedQuery<Fabricante> q = em.createQuery(jpql, Fabricante.class);
		Set<Fabricante> set = new HashSet<Fabricante>(q.getResultList());
		em.close();
 		return set;
	}

}
