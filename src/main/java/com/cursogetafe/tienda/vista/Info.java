package com.cursogetafe.tienda.vista;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/WEB-INF/informacion")
public class Info extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		System.out.println(req.getAttribute("origen"));
		
		
		out.println("Metodos de la peticion");
		
		out.println("req.getCharacterEncoding():: " + req.getCharacterEncoding());
		out.println("req.getContentType():: " + req.getContentType());
		out.println("req.getContextPath():: " + req.getContextPath());
		out.println("(req.getLocalAddr():: " + req.getLocalAddr());
		out.println("(req.getLocalPort():: " + req.getLocalPort());
		out.println("req.getMethod():: " + req.getMethod());
		out.println("req.getProtocol():: " + req.getProtocol());
		out.println("(req.getRemoteAddr():: " + req.getRemoteAddr());
		out.println("req.getRequestURI():: " + req.getRequestURI());
		out.println("req.getPathInfo() :: " + req.getPathInfo());
		out.println("req.getSession() :: " + req.getSession());
		
	}

}
