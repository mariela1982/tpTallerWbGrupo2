package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;

public class ControladorLoginTest {

	private ControladorLogin controladorLogin;
	private Usuario usuarioMock;
	private DatosLogin datosLoginMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioLogin servicioLoginMock;


	@BeforeEach
	public void init(){
		datosLoginMock = new DatosLogin("mari@unlam.com", "123");
		usuarioMock = mock(Usuario.class);
		when(usuarioMock.getEmail()).thenReturn("mari@unlam.com");
		when(usuarioMock.getPassword()).thenReturn("123");
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioLoginMock = mock(ServicioLogin.class);
		controladorLogin = new ControladorLogin(servicioLoginMock);
	}

	@Test
	public void queAlTocarElBotonIngresarVayaAlaVistaPrincipal(){
		ModelAndView modelAndView = controladorLogin.homePrincipal();
		String nombre = modelAndView.getViewName();
		assertThat(nombre, equalToIgnoringCase("homePrincipal"));
	}

	@Test
	public void queAlTocarElBotonLoginvayaAlaVistaLogin(){
		ModelAndView modelAndView = controladorLogin.irALogin();
		String nombre = modelAndView.getViewName();
		assertThat(nombre, equalToIgnoringCase("login"));
	}

	@Test
	public void queAlTocarElBotonRegistratevayaAlaVistaNuevoUsuario(){
		ModelAndView modelAndView = controladorLogin.nuevoUsuario();
		String nombre = modelAndView.getViewName();
		assertThat(nombre, equalToIgnoringCase("nuevoUsuario"));
	}

	@Test
	public void loginConUsuarioYPasswordInorrectosDeberiaLlevarALoginNuevamente(){
		// preparacion
		when(servicioLoginMock.consultarUsuario(anyString(), anyString())).thenReturn(null);

		// ejecucion
		ModelAndView modelAndView = controladorLogin.validarLogin(datosLoginMock, requestMock);

		// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("login"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Usuario o clave incorrecta"));

	}
	
//	@Test
//	public void loginConUsuarioYPasswordCorrectosDeberiaLLevarAHome(){
//		// preparacion
//
//		when(usuarioMock.getAdmin()).thenReturn(false);
//
//		when(requestMock.getSession()).thenReturn(sessionMock);
//		when(servicioLoginMock.consultarUsuario(anyString(), anyString())).thenReturn(usuarioMock);
//
//		// ejecucion
//		ModelAndView modelAndView = controladorLogin.validarLogin(datosLoginMock, requestMock);
//
//		// validacion
//		assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/homePrincipal"));
//
//	}

	@Test
	public void registrameSiUsuarioNoExisteDeberiaCrearUsuarioYVolverAlLogin() throws UsuarioExistente {

		// ejecucion
		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

		// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
		verify(servicioLoginMock, times(1)).registrar(usuarioMock);
	}

	@Test
	public void registrarmeSiUsuarioExisteDeberiaVolverAlHomeYMostrarError() throws UsuarioExistente {
		// preparacion
		doThrow(UsuarioExistente.class).when(servicioLoginMock).registrar(usuarioMock);

		// ejecucion
		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

		// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("homePrincipal"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("El usuario ya existe"));
	}

	@Test
	public void errorEnRegistrarmeDeberiaVolverAlHomeYMostrarError() throws UsuarioExistente {
		// preparacion
		doThrow(RuntimeException.class).when(servicioLoginMock).registrar(usuarioMock);

		// ejecucion
		ModelAndView modelAndView = controladorLogin.registrarme(usuarioMock);

		// validacion
		assertThat(modelAndView.getViewName(), equalToIgnoringCase("homePrincipal"));
		assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Error al registrar el nuevo usuario"));
	}
}
