//package com.tallerwebi.presentacion;
//
//import com.tallerwebi.dominio.RepositorioUsuario;
//import com.tallerwebi.dominio.ServicioUsuario;
////import com.tallerwebi.dominio.ServicioUsuarioImpl;
//import com.tallerwebi.dominio.Usuario;
//import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
//import com.tallerwebi.infraestructura.RepositorioUsuarioImpl;
//import org.junit.jupiter.api.Test;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.notNullValue;
//import static org.hamcrest.Matchers.nullValue;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.ExpectedCount.times;
//
//public class ServicioUsuarioTest {
//
//
//    RepositorioUsuario repositorioUsuario = mock(RepositorioUsuarioImpl.class);
//    ServicioUsuario servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario);
//
//    @Test
//    public void siExisteEmailYPasswordElRegistroEsExitoso() throws PasswordLongitudIncorrecta {
//
//
//        Usuario usuarioCreado =  whenRegistroUsuario("kvn@gmail.com","12345");
//
//
//
//        thenElRegistroEsExitoso(usuarioCreado);
//    }
//
//    private void thenElRegistroEsExitoso(Usuario usuarioCreado) {
//        assertThat(usuarioCreado, notNullValue());
//
//    }
//
//    private Usuario whenRegistroUsuario(String email, String password) throws PasswordLongitudIncorrecta {
//       Usuario usuarioCreado = servicioUsuario.guardarEquipo(email,password);
//        return usuarioCreado;
//    }
//
//    @Test
//    public void siLaPasswordTieneMenosDe5CaracteresElRegistroFalla(){
//
//        assertThrows(PasswordLongitudIncorrecta.class, ()-> whenRegistroUsuario("kvn@gmail.com","123"));
//
//
//    }
//
//    private void thenElRegistroFalla(Usuario usuarioCreado) {
//        assertThat(usuarioCreado, nullValue());
//
//    }
//
//
//    @Test
//    public void siYaExisteUsuarioConMismoEmailElRegistroFalla() throws PasswordLongitudIncorrecta {
//
//        givenExisteUsuario("kvn@gmail.com","12345");
//
//
//
//        when(repositorioUsuario.buscar("kvn@gmail.com")).thenReturn(new Usuario());
//        Usuario usuario = whenRegistroUsuario("kvn@gmail.com","12345");
//
//
//        thenElRegistroFalla(usuario);
//
//    }
//
//    private void givenExisteUsuario(String email, String password) throws PasswordLongitudIncorrecta {
//        whenRegistroUsuario(email,password);
//    }
//}
