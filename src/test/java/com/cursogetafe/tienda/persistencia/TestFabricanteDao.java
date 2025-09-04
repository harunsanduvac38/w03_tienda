package com.cursogetafe.tienda.persistencia;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.cursogetafe.tienda.modelo.Fabricante;



class TestFabricanteDao {

	static FabricanteDao fDao;
	static Connection con;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		fDao = new FabricanteDaoJPA();
	}

	@Test
	void testSave() throws Exception{
		Fabricante f = new Fabricante();
		f.setFabricante("Fabricante Nuevo Test");
		int cantAnterior = cantidadFabricantes();
		fDao.save(f);
		assertEquals(cantidadFabricantes(), cantAnterior+1);
		con = FuenteDatos.getConnection();
		String sql = "delete from fabricantes where fabricante = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "Fabricante Nuevo Test");
		int borrado = ps.executeUpdate();
		assertEquals(1, borrado);
		con.close();
	}
	
	@Test
	void testFindByIdExiste() {
		Fabricante fExiste = fDao.findById(6);
		assertNotNull(fExiste);
		assertEquals("Crucial", fExiste.getFabricante());
		assertEquals(2, fExiste.getProductos().size());
	}

	@Test
	void testFindByIdNoExiste() {
		Fabricante fNoExiste = fDao.findById(66);
		assertNull(fNoExiste);
	}
	
	@Test
	void testFindByIdLazyExiste() {
		Fabricante fExiste = fDao.findByIdLazy(5);
		assertNotNull(fExiste);
		assertEquals("Seagate", fExiste.getFabricante());
		Executable sizeProd = () -> {fExiste.getProductos().size(); };
		assertThrows(LazyInitializationException.class, sizeProd, "");
	}
	
	@Test
	void testFindByIdLazyNoExiste() {
		Fabricante fNoExiste = fDao.findByIdLazy(555);
		assertNull(fNoExiste);
	}
	
	@Test
	void testFindOnlyActive() throws Exception {
		Set<Fabricante> fabricantes = fDao.findOnlyActive();
		assertNotNull(fabricantes);
		assertEquals(cantidadFabricantesActivos(), fabricantes.size());
	}

	private int cantidadFabricantesActivos() throws Exception{
		con = FuenteDatos.getConnection();
		String sql = 
			"""
			select count(distinct id_fabricante) from fabricantes
			join productos on id_fabricante = fk_fabricante
			""";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int cant = rs.getInt(1);
		con.close();
		return cant;
	}

	private int cantidadFabricantes() throws Exception{
		con = FuenteDatos.getConnection();
		String sql = 
				"""
				select count(distinct id_fabricante) from fabricantes
				""";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int cant = rs.getInt(1);
		con.close();
		return cant;
	}
	
}
