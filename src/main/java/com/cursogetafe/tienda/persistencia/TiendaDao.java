package com.cursogetafe.tienda.persistencia;

import com.cursogetafe.tienda.config.Config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class TiendaDao {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public TiendaDao() {
		emf = Config.getEmf();
	}
	
	
	public TiendaDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	

}
