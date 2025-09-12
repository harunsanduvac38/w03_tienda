package com.cursogetafe.tienda.persistencia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cursogetafe.tienda.modelo.Fabricante;
import com.cursogetafe.tienda.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;


class FabricanteDaoJPATest {
	
	
	private static EntityManagerFactory emf;
	private static FabricanteDao fabDao;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("tiendaTest");
		fabDao = new FabricanteDaoImpl(emf);
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		
		String jpql = "delete from Fabricante";
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery(jpql);
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	@Test
	void testSave() {
	

		
	}

	@Test
	void testFindByIdLazy() {
		
	
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindOnlyActive() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}
	
	
	
	private  Fabricante crearFabricante(int nroProd) {
		Fabricante nuevo = new Fabricante(0, "fabricante");
		Set<Producto> set = new LinkedHashSet<Producto>(nroProd);
		for(int i = 1; i <= nroProd; i++) {
			Producto producto = new Producto(0, "Producto" + nroProd, 100);
			set.add(producto);
		}
		nuevo.setProductos(set);
		return nuevo;
		
		
		
		
	}

}
