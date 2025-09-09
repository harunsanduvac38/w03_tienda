package com.cursogetafe.tienda.vista;

import java.io.IOException;
import java.util.Set;

import com.cursogetafe.tienda.modelo.Fabricante;
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
			Set<Fabricante> fabs = neg.getFabricantes();
			req.setAttribute("fabs", fabs);
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
		
			double precio;
			Fabricante fab;
			
			if(!isEmpty(descripcion)
					&& !isEmpty(precioStr)
					&& !isEmpty(idFabStr)
					&& isDouble(precioStr)
					&& isInteger(idFabStr)
					&& (precio = Double.parseDouble(precioStr)) > 0
					&& (fab = neg.getFabricante(Integer.parseInt(idFabStr))) != null) {
				req.setAttribute("producto", descripcion);
				
				try {
					Producto prod = new Producto(0, descripcion, Double.valueOf(precioStr));
					prod.setFabricante(fab);
					neg.crearProducto(prod);
					
					req.getRequestDispatcher("/WEB-INF/vista/alta_producto_ok.jsp").forward(req, resp);
				} catch (Exception e) {
					e.printStackTrace();
					req.getRequestDispatcher("/WEB-INF/vista/alta_producto_error.jsp").forward(req, resp);
				}
				
			} else {
				//cerrar sessión!!
				System.out.println(descripcion);
				System.out.println(precioStr);
				System.out.println(idFabStr);
				System.out.println("dio error");
			}
			
			
			
		
			System.out.println(descripcion);
			System.out.println(precioStr);
			System.out.println(idFabStr);
			
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
	
	
	public boolean isEmpty(String param) {
		return param == null || param.trim().length() == 0;
	}
	
	public boolean isDouble(String num){
		try {
			Double.parseDouble(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isInteger(String num){
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	

	
	
	
	

}
