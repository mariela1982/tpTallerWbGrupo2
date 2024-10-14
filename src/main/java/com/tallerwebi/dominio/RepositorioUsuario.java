package com.tallerwebi.dominio;

public interface RepositorioUsuario {

    Usuario buscarUsuario(String email, String password);
    void guardar(Usuario usuario);

    void modificar(Usuario usuario);


    void actualizarSaldo(Usuario usuario,Integer saldo);
}

