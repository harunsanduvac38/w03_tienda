package com.cursogetafe.tienda.persistencia;

import java.util.LinkedList;
import java.util.List;

import com.cursogetafe.tienda.config.Config;
import com.cursogetafe.tienda.excepciones.PersistenciaException;
import com.cursogetafe.tienda.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class ProductoDaoJPA implements ProductoDao {
	
	private EntityManagerFactory emf;
	EntityManager em;
	
	public ProductoDaoJPA() {
		emf = Config.getEmf();
	}
	
	public ProductoDaoJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Producto findById(int idProducto) {
		em = emf.createEntityManager();
		
		Producto p = em.find(Producto.class, idProducto);
		em.close();
		return p;
	}

	@Override
	public List<Producto> findByDescripcion(String descripcion) {
		em = emf.createEntityManager();
		String jpql =  "select p from Producto p where p.producto like :des";
		TypedQuery<Producto> q = em.createQuery(jpql, Producto.class);
		q.setParameter("des","%" + descripcion + "%");
		List<Producto> list = new LinkedList<Producto>(q.getResultList());
		em.close();
		
		
		return list;
	}

	@Override
	public List<Producto> findAll() {
		em = emf.createEntityManager();
		
		String jpql = "select p from Producto p";
		TypedQuery<Producto> q = em.createQuery(jpql, Producto.class);
		List<Producto> list = new LinkedList<Producto>(q.getResultList());
		em.close();
		return list;
	}

	@Override
	public void save(Producto p) {
		EntityManager em = emf.createEntityManager();
		
		try(em) {
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new PersistenciaException();
		}
		
		
	}
	
	

}
