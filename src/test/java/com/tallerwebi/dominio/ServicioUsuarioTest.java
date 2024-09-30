package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
import com.tallerwebi.infraestructura.ServicioUsuarioImpl;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServicioUsuarioTest {

    ServicioUsuario servicioUsuario = new ServicioUsuarioImpl();
    //para no crear un constructor por defecto utilizo mock

    @Test
    public void siExisteEmailYpasssElRegistroEsExitoso() throws PasswordLongitudIncorrecta {
        //given

        //when
        Usuario usuarioCreado=  whenRegistroUsuario("m@gmail.com","123456");
        //then
        thenElRegistroEsExitoso(usuarioCreado);
    }

    private void thenElRegistroEsExitoso(Usuario usuarioCreado) {
        //como se que el registro es exitoso ,si el metodo registrar me devuelve un usurio)
        assertThat(usuarioCreado, notNullValue());

    }

    private Usuario whenRegistroUsuario(String mail, String password) throws PasswordLongitudIncorrecta {
        Usuario usuarioCreado =servicioUsuario.registrar(mail,password);
        return usuarioCreado;
    }
    // necesitamos hacer mas test por logica de negocio por ejemplo que tipo de contraseña aceptar
    @Test
    public void siLaPasssTieneMenosDe5CaracteresElRegistroFalla() throws PasswordLongitudIncorrecta {
        //given

        //when
       // Usuario usuarioCreado =whenRegistroUsuario("m@gmail.com","1234");
       //aca en el assert ya
        assertThrows(PasswordLongitudIncorrecta.class,()-> whenRegistroUsuario("m@gmail.com","123456"));
        //then
       // thenElRegistroFalla(usuarioCreado);
    }

    private void thenElRegistroFalla(Usuario usuarioCreado) {
        assertThat(usuarioCreado, nullValue());
        //ahora vamos a querer que largue una excepcion no un null ;


    }

    @Test
    public void siYaExisteUnUsuarioConElMismoMailElRegistroFalla() throws PasswordLongitudIncorrecta {
        //given
        givenRegistroUsuario("m@gmail","12345");
        Usuario usuarioCreado= whenRegistroUsuario("m@gmail.com","123456");
        thenElRegistroFalla(usuarioCreado);
    }

    private void givenRegistroUsuario(String s, String number) throws PasswordLongitudIncorrecta {
        whenRegistroUsuario("m@gmail.com","123456");
    }
}
