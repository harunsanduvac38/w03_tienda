package com.cursogetafe.tienda.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Config {
	
	private static EntityManagerFactory emf;
	
	public Config() {}
	
	public static EntityManagerFactory getEmf() {
		
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("tienda");
		}
		return emf;
	}

}
