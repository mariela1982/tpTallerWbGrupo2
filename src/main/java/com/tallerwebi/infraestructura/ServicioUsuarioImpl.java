package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ServicioUsuarioImpl implements ServicioUsuario {

    RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    public ServicioUsuarioImpl() {

    }

    @Override
    public Usuario registrar(String email, String password) throws PasswordLongitudIncorrecta {

        if (email == null || password == null || password.length() < 5){
            throw new PasswordLongitudIncorrecta();
        }
        Usuario usuarioBuscado = repositorioUsuario.buscar(email);
        if (usuarioBuscado != null){
            return null;
        }
        return new Usuario();
    }
}
