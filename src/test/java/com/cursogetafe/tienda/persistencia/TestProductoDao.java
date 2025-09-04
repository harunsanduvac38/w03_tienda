package com.cursogetafe.tienda.persistencia;

import com.cursogetafe.tienda.modelo.Producto;

public class TestProductoDao {
	public static void main(String[] args) {
		
	ProductoDaoJPA prodjpa = new ProductoDaoJPA();
	FabricanteDaoJPA fabjpa = new FabricanteDaoJPA();
	
	System.out.println(prodjpa.findByDescripcion("monitor"));
	
	for(Producto producto : prodjpa.findAll()) {
		System.out.println(producto);
	}
	
	
	
//	Fabricante fabricante = new Fabricante(0, "Nvidia");
//	
//	fabjpa.save(fabricante);
	
	
	Producto producto = new Producto(0, "Nvidia RTX 5090 Ti 24 GB", 1250.0);
	producto.setFabricante(fabjpa.findByIdLazy(12));
	
	prodjpa.save(producto);
	

	//	System.out.println(prodjpa.findByDescripcion("NVIDIA"));
//	
//			
//	Producto prodnuevo = new Producto(0, "Rog Strix G18", 1900);
//	prodnuevo.setFabricante(fabjpa.findByIdLazy(1));
//
//	prod.save(prodnuevo);
//	prodnuevo.setIdProducto(13);
//	prodjpa.save(prodnuevo);
	
//	prodjpa.findById(14).setIdProducto(13);
//	System.out.println(prodjpa.findById(14));
//	prodjpa.save(prodjpa.findById(13));
			
			
	

	
	
	
	}
}
