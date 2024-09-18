package com.tallerwebi.dominio;


import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface ServicioUsuario {
    Usuario registrar(String email, String password) throws PasswordLongitudIncorrecta;
}
