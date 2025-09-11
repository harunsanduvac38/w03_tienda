package com.cursogetafe.tienda.vista;

import java.io.IOException;
import java.util.Set;

import com.cursogetafe.tienda.modelo.Fabricante;
import com.cursogetafe.tienda.modelo.Producto;
import com.cursogetafe.tienda.negocio.Tienda;
import com.cursogetafe.tienda.negocio.TiendaImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/tienda/*")
public class Controller extends HttpServlet{
	
	private Tienda neg;
	private String home;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		HttpSession sesion = req.getSession();
		
		String path = req.getPathInfo();
		Set<Fabricante> fabs;
		
		switch(path) {
		case "/informacion":
			req.setAttribute("origen", "el que te envio esto fuio yo, el Controlador!");
			req.getRequestDispatcher("/WEB-INF/informacion").forward(req, resp);
			break;
		case"/menu_principal":
			req.getRequestDispatcher("/WEB-INF/vista/menu_principal.jsp").forward(req, resp);

			eliminaDatosSesion(sesion);
			break;
		case "/listado_productos":
			req.getRequestDispatcher("/WEB-INF/vista/listado_productos.jsp").forward(req, resp);			
			break;
		case "/alta_producto":
			fabs = neg.getFabricantes();
			req.setAttribute("fabs", fabs);
			req.getRequestDispatcher("/WEB-INF/vista/alta_producto.jsp").forward(req, resp);
			break;
		case "/alta_producto_ok":
			req.getRequestDispatcher("/WEB-INF/vista/alta_producto_ok.jsp").forward(req, resp);
			
			break;
		case "/alta_producto_error":
			req.getRequestDispatcher("/WEB-INF/vista/alta_producto_error.jsp").forward(req, resp);
			break;
		case "/productos_fabricante":
			fabs = neg.getFabricantesActivos();
			req.setAttribute("fabs", fabs);
			req.getRequestDispatcher("/WEB-INF/vista/productos_fabricante.jsp").forward(req, resp);
			break;
		case "/productos_fabricante_json":
			fabs = neg.getFabricantesActivos();
			req.setAttribute("fabs", fabs);
			req.getRequestDispatcher("/WEB-INF/vista/productos_fabricante_json.jsp").forward(req, resp);
			break;
			
		}
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getPathInfo();

		
		HttpSession sesion = req.getSession();
		
		
		String descripcion;
		String idFabStr;
		String precioStr;
		Fabricante fab;
		
		
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
			precioStr =  req.getParameter("precio");	
			idFabStr = req.getParameter("idFabricante");
		
			double precio;
			
			
			if(!isEmpty(descripcion)
					&& !isEmpty(precioStr)
					&& !isEmpty(idFabStr)
					&& isDouble(precioStr)
					&& isInteger(idFabStr)
					&& (precio = Double.parseDouble(precioStr)) > 0
					&& (fab = neg.getFabricante(Integer.parseInt(idFabStr))) != null) {
				
				sesion.setAttribute("producto", descripcion);
				
				try {
					Producto prod = new Producto(0, descripcion, Double.valueOf(precioStr));
					prod.setFabricante(fab);
					neg.crearProducto(prod);
					
					resp.sendRedirect(home + "/alta_producto_ok");
					
				} catch (Exception e) {
					
					resp.sendRedirect(home + "/alta_producto_error");
				}
				
			} else {
				//cerrar sessión!!
				System.out.println(descripcion);
				System.out.println(precioStr);
				System.out.println(idFabStr);
				System.out.println("dio error");
			}
					
			break;
		case "/productos_fabricante":
			
			idFabStr = req.getParameter("idFabricante");
			if(!isEmpty(idFabStr)
				&& isInteger(idFabStr)
				&& (fab = neg.getFabricante(Integer.valueOf(idFabStr))) != null) {
				
				sesion.setAttribute("fab", fab);
				resp.sendRedirect(home + "/productos_fabricante");
			
				
			} else {
				//cerrar sesion!!
				System.out.println(idFabStr);
				System.out.println("dio error");
			}
			break;
		case "/productos_fabricante_json_respuesta":
			idFabStr = req.getParameter("idFabricante");
			if(!isEmpty(idFabStr)
				&& isInteger(idFabStr)
				&& (fab = neg.getFabricante(Integer.valueOf(idFabStr))) != null) {
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(fab.getProductos());
				resp.setContentType("application/json;charset=UTF-8");
				resp.getWriter().println(json);
			
				
			} else {
				//cerrar sesion!!
				System.out.println(idFabStr);
				System.out.println("dio error");
			}
			break;
		}
	}
	
	
	@Override
	public void init() throws ServletException {
		
		neg = new TiendaImpl();

		ServletContext app = getServletContext();
		
		home = app.getContextPath() + "/tienda";
		
		app.setAttribute("home", home);
		app.setAttribute("css", app.getContextPath() + "/css");
	}
	
	
	private boolean isEmpty(String param) {
		return param == null || param.trim().length() == 0;
	}
	
	private boolean isDouble(String num){
		try {
			Double.parseDouble(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isInteger(String num){
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	

	private void eliminaDatosSesion(HttpSession sesion) {
		sesion.removeAttribute("fab");
		sesion.removeAttribute("fabs");
		sesion.removeAttribute("prods");
	}

}
