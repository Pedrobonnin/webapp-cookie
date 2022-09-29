package org.pab.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.pab.webapp.servlet.services.LoginService;
import org.pab.webapp.servlet.services.LoginServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;


@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "123456";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();


        Optional<String> cookieOptional = auth.getUsername(req);
        if (cookieOptional.isPresent()){ // con isPresent manejamos la cookies "si hay cookie presente inicia"
            resp.setContentType("text/html");


            try(PrintWriter out = resp.getWriter()){


                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <link rel=\"stylesheet\" href=\"style.css\">");
                out.println("        <title>Hola "+ cookieOptional.get()+"</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Hola "+ cookieOptional.get()+ " ya has iniciado sesion anteriormente! </h1>");

                out.println("        <div id=\"lista\">");
                out.println("            <ul>");
                out.println("               <li><a href='"+req.getContextPath()+"/index.html'>volver</a></li>");
                out.println("               <li><a href='"+req.getContextPath()+"/productos.html'>Lista de Productos</a></li>");
                out.println("               <li><a href='"+req.getContextPath()+"/logout'>cerrar sesion</a></li>");
                out.println("            <ul>");
                out.println("        </div>");
                out.println("    </body>");
                out.println("</html>");

            }


        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        List<String> errores = new ArrayList<>();
        if (username == null || username.isEmpty()) { //valida el ingleso de dato

            errores.add("El usuario no puede ser vacio");
        }

        if (password == null || password.isEmpty()) {

            errores.add("La clave no puede ser vacio");
        }

        if (errores.isEmpty()) {
            //Creamos una nueva Cookie

            if (USERNAME.equals(username) && PASSWORD.equals(password)){ // valida la existencia de usuario
                Cookie usernameCookie = new Cookie("username",username);



                resp.addCookie(usernameCookie);
                resp.sendRedirect(req.getContextPath()+"/login.html");


            }else {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "El usuario o la contraseña no coinciden");
            }


        } else {

            req.setAttribute("errores",errores);//atributos de request. Nos permiten pasar datos de un servlet a una JSP o de servlet a servlet

            //Redireccionamos a la JSP desde el servlet
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);//metodo para cargar la JSP-> forward

        }
    }
}
