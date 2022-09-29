package org.pab.webapp.servlet.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceImpl implements LoginService{
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {//Obtenemos la cookie
        Cookie[] cookies = req.getCookies() != null ? req.getCookies(): new Cookie[0]; //Leemos la cookie
        return Arrays.stream(cookies)
                .filter(c-> "username".equals(c.getName())) //buscamos la Cookie
                .map(Cookie::getValue) //para convertir de un flujo a otro de un tipo Cookie a un tipo String
                .findAny(); //obtenemos la cookie
    }
}
