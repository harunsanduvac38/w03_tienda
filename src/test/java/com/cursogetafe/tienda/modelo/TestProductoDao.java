package com.cursogetafe.tienda.modelo;

import com.cursogetafe.tienda.persistencia.FabricanteDaoJPA;
import com.cursogetafe.tienda.persistencia.ProductoDaoJPA;

public class TestProductoDao {
	public static void main(String[] args) {
		
	ProductoDaoJPA p = new ProductoDaoJPA();
	FabricanteDaoJPA f = new FabricanteDaoJPA();
	
	System.out.println(p.findByDescripcion("monitor"));
	
	System.out.println(p.findAll());
	
	
	
	Fabricante fabricante = new Fabricante(0, "Nvidia");
	
	f.save(fabricante);
	
	
	Producto producto = new Producto(0, "Nvidia RTX 5090 Ti 24 GB", 1250.0);
	producto.setFabricante(f.findByIdLazy(12));
	
	p.save(producto);
	
	System.out.println(producto);
	System.out.println(producto.getFabricante());
	
			
			
			
			
			
	}
}
