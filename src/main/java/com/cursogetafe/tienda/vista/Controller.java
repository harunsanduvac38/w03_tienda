package com.cursogetafe.tienda.vista;

import java.io.IOException;
import java.util.Set;

import com.cursogetafe.tienda.modelo.Producto;
import com.cursogetafe.tienda.negocio.Tienda;
import com.cursogetafe.tienda.negocio.TiendaImpl;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tienda/*")
public class Controller extends HttpServlet{
	
	private Tienda neg;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		String path = req.getPathInfo();
		
		switch(path) {
		case "/informacion":
			req.setAttribute("origen", "el que te envio esto fuio yo, el Controlador!");
			req.getRequestDispatcher("/WEB-INF/informacion").forward(req, resp);
			break;
		case"/menu_principal":
			req.getRequestDispatcher("/WEB-INF/vista/menu_principal.jsp").forward(req, resp);
			break;
		case "/listado_productos":
			req.getRequestDispatcher("/WEB-INF/vista/listado_productos.jsp").forward(req, resp);			
			break;
		case "/alta_producto":
			req.getRequestDispatcher("/WEB-INF/vista/alta_producto.jsp").forward(req, resp);
			break;
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getPathInfo();
		String descripcion;
		
		switch(path) {
		case"/listado_productos":
			descripcion = req.getParameter("descripcion");
			Set<Producto> prods;
			if(descripcion != null && descripcion.length() > 0) {
				prods = neg.getProductos(descripcion);
			}else {
				prods = neg.getProductos();
			}
			req.setAttribute("prods", prods);
			req.getRequestDispatcher("/WEB-INF/vista/listado_productos.jsp").forward(req, resp);
			
			break;
		case "/alta_producto":
			descripcion = req.getParameter("descripcion");
			String precioStr =  req.getParameter("precio");
			String idFabStr = req.getParameter("idFabricante");
			
			
			
			
			break;
			
			
		}
	}
	
	
	@Override
	public void init() throws ServletException {
		
		neg = new TiendaImpl();

		ServletContext app = getServletContext();
		
		app.setAttribute("home", app.getContextPath() + "/tienda");
		app.setAttribute("css", app.getContextPath() + "/css");
		
		
		
	}
	

	
	
	
	

}
