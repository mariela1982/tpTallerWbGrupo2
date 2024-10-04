package com.tallerwebi.dominio;


import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;


public interface ServicioUsuario {
    Usuario registrar(String email, String password) throws PasswordLongitudIncorrecta;
}
