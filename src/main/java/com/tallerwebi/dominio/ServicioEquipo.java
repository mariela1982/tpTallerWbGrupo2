package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.CamposVacios;
import com.tallerwebi.dominio.excepcion.EquipoExistente;

public interface ServicioEquipo {

    Equipo buscarEquipoPorNombre(String nombre);


    Equipo registrar(String nombre, String numeroCbu, Long dtDni) throws EquipoExistente;
}
