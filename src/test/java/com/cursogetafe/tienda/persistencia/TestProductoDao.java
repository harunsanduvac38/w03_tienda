package com.cursogetafe.tienda.persistencia;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cursogetafe.tienda.modelo.Producto;


class TestProductoDao {

	static ProductoDao pDao;
	static Connection con;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pDao = new ProductoDaoJPA();
	}

	private int cantidadProductos(String sql) throws Exception{
		con = FuenteDatos.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int cant = rs.getInt(1);
		con.close();
		return cant;
	}

	@Test
	void testFindByIdExiste() {
		Producto pExiste = pDao.findById(10);
		assertNotNull(pExiste);
		assertEquals("Impresora HP Deskjet 3720", pExiste.getProducto());
	}

	@Test
	void testFindByIdNoExiste() {
		Producto pNoExiste = pDao.findById(100);
		assertNull(pNoExiste);
	}
	
	@Test
	void testFindByDescripcion() throws Exception{
		List<Producto> buscados = pDao.findByDescripcion("disco");
		assertNotNull(buscados);
		String sql = "select count(*) from productos where producto like '%disco%'";
		assertEquals(cantidadProductos(sql), buscados.size());
	}
	
	@Test
	void testFindByDescripcionVacia() {
		List<Producto> buscados = pDao.findByDescripcion("disk");
		assertNotNull(buscados);
		assertEquals(0, buscados.size());
	}
	
	@Test
	void testFindAll() throws Exception{
		List<Producto> buscados = pDao.findAll();
		assertNotNull(buscados);
		String sql = "select count(*) from productos";
		assertEquals(cantidadProductos(sql), buscados.size());
	}
}
