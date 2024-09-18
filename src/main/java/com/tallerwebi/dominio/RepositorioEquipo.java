package com.tallerwebi.dominio;

public interface RepositorioEquipo {
    Equipo buscar(String nombre);

    void guardar(Equipo equipoCreado);
}
