package com.cursogetafe.tienda.json;

import com.cursogetafe.tienda.modelo.Producto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJakson02 {
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		
		String json = "{\"idProducto\":7,\"producto\":\"Monitor 27 LED Full HD\","
				+ "\"precio\":245.99,\"fabricante\":{\"idFabricante\":1,\"fabricante\":\"Asus\"}}";
		
		System.out.println(json);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Producto p = mapper.readValue(json, Producto.class);
		
		System.out.println(p);
		System.out.println(p.getProducto());
		
		
		
	}

}
