package com.cursogetafe.tienda.negocio;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import com.cursogetafe.tienda.modelo.Fabricante;
import com.cursogetafe.tienda.modelo.Producto;
import com.cursogetafe.tienda.persistencia.FabricanteDao;
import com.cursogetafe.tienda.persistencia.FabricanteDaoJPA;
import com.cursogetafe.tienda.persistencia.ProductoDao;
import com.cursogetafe.tienda.persistencia.ProductoDaoJPA;
import com.cursogetafe.tienda.vista.Controller;



public class TiendaImpl implements Tienda{
	
	
	private ProductoDao pDao;
	private FabricanteDao fDao;
	
	public TiendaImpl() {
		pDao = new ProductoDaoJPA();
		fDao = new FabricanteDaoJPA();
	}

	@Override
	public Set<Producto> getProductos() {
		Set<Producto> resu = new TreeSet<Producto>(getComparatorProductoDesc());
		resu.addAll(pDao.findAll());
		return resu;
	}

	@Override
	public Set<Producto> getProductos(String descripción) {
		Set<Producto> resu = new TreeSet<Producto>(getComparatorProductoDescLambda());
		resu.addAll(pDao.findByDescripcion(descripción));
		return resu;
	}
	
	public Set<Fabricante> getFabricantes() {
		Set<Fabricante> resu = new TreeSet<Fabricante>(getComparatorFabricanteDesc());
		resu.addAll(fDao.findAll());
		return resu;
	}
	
	public Fabricante getFabricante(int idFabricante) {
		return fDao.findById(idFabricante);
	}
	
	
	@Override
	public Set<Fabricante> getFabricantesActivos() {
		Set<Fabricante> resu = new TreeSet<Fabricante>(getComparatorFabricanteDesc());
		resu.addAll(fDao.findOnlyActive());	
		return resu;
	}
	
	
	@Override
	public void crearProducto(Producto producto) {
		pDao.save(producto);
		
		
	}
	
	
	private Comparator<Producto> getComparatorProductoDesc(){
		return new Comparator<Producto>() {
			
			@Override
			public int compare(Producto o1, Producto o2) {
				Collator col = Collator.getInstance(new Locale("es"));
				
				return col.compare(o1.getProducto(), o2.getProducto());
			}
		};
	}
	
	
	private Comparator<Producto> getComparatorProductoDescLambda(){
		return (o1, o2) -> Collator.getInstance(new Locale("es")).compare(o1.getProducto(), o2.getProducto());
	};
	
	
	private Comparator<Producto> getComparatorProductoIdLambda(){
		return (p1, p2) -> p1.getIdProducto() - p2.getIdProducto();
	};
	
	
	private Comparator<Fabricante> getComparatorFabricanteDesc(){
		return (f1, f2) -> Collator.getInstance(new Locale("es")).compare(f1.getFabricante(), f2.getFabricante());
	}


	
	

}
