package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.RepositorioEquipo;
import com.tallerwebi.dominio.ServicioEquipo;
import com.tallerwebi.dominio.excepcion.CamposVacios;
import com.tallerwebi.dominio.excepcion.EquipoExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioEquipoImpl implements ServicioEquipo {


RepositorioEquipo repositorioEquipo;

@Autowired
public ServicioEquipoImpl(RepositorioEquipo repositorioEquipo) {
    this.repositorioEquipo = repositorioEquipo;
}



    @Override
    public Equipo buscarEquipoPorNombre(String nombre) {
        return repositorioEquipo.buscar(nombre);
    }

    @Override
    public Equipo registrar(String nombre, String numeroCbu, Long dtDni) throws EquipoExistente {

        if(nombre.isEmpty() || numeroCbu.isEmpty() || dtDni == null){
                return null;
            }

        Equipo equipo = repositorioEquipo.buscar(nombre);




        if(equipo != null){
            return null;
        }
        else{
            Equipo equipoACrear = new Equipo();
            equipoACrear.setNombre(nombre);
            equipoACrear.setCbu(numeroCbu);
            equipoACrear.setDtDni(dtDni);
            repositorioEquipo.guardar(equipoACrear);
            equipo = equipoACrear;

        }
        return equipo;
    }


    }


