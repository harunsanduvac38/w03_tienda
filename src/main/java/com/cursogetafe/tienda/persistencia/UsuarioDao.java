package com.cursogetafe.tienda.persistencia;

import com.cursogetafe.tienda.modelo.Usuario;

public interface UsuarioDao {
	
	
	Usuario findById(int id);
	
	
	boolean save(Usuario usuario);
	
	
	Usuario valida(String usuario, String password);
	
	
	
	

}
