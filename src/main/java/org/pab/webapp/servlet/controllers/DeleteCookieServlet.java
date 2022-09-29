package org.pab.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


        //La API de Servlet no proporciona una forma directa de eliminar
        //una cookie en una aplicación de Servlet. Si desea eliminar una cookie,
        //debe crear una cookie que tenga el mismo nombre que la cookie que desea
        //eliminar y establecer el valor en una cadena vacía. También debe establecer
        //la antigüedad máxima de la cookie en 0. Y luego agregue esta cookie al objeto
        //de respuesta del servlet.

@WebServlet({"/logout"})
public class DeleteCookieServlet extends HttpServlet{


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        // Para eliminar una cookie, necesitamos crear una cookie que tenga el mismo
        // nombre con la cookie que queremos eliminar. También tenemos que establecer
        // la edad máxima de la cookie a 0 y luego agregarla al Servlet
        // método de respuesta.


        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);   //Tiempo en segundo que estara abierta la sesion
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}