package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidades.Jugador;
import com.tallerwebi.dominio.entidades.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;

public interface ServicioLogin {

    Usuario consultarUsuario(String email, String password);
    void registrar(Usuario usuario) throws UsuarioExistente;

    void modificar(Usuario usuario);

    void actualizarSaldo(Usuario usuario,Integer saldo);

    void guardarJugadorCreadoPorDt(Integer id, Jugador jugador);
}
