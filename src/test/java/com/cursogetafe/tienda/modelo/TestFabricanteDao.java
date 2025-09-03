package com.cursogetafe.tienda.modelo;

import com.cursogetafe.tienda.persistencia.FabricanteDaoJPA;

public class TestFabricanteDao {
	public static void main(String[] args) {
		
		FabricanteDaoJPA f = new FabricanteDaoJPA();
		
		System.out.println(f.findById(5));
		
		System.out.println(f.findByIdLazy(5));
		
		System.out.println(f.findOnlyActive());
		
		System.out.println(f.findAll());
		
		
		Fabricante fab = new Fabricante(0, "Legion");
		
		f.save(fab);
		
		
	}

}
