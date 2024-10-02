package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.enums.Localidades;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ControladorLogin {

    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin){

        this.servicioLogin = servicioLogin;
    }

    @RequestMapping("/login")
    public ModelAndView irALogin() {

        ModelMap modelo = new ModelMap();
        modelo.put("datosLogin", new DatosLogin());
        return new ModelAndView("login", modelo);
    }

    @RequestMapping(path = "/validarLogin", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());

        if (usuarioBuscado != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuarioBuscado);
            return new ModelAndView("redirect:/directorTecnico");
        }
        else if (usuarioBuscado != null && usuarioBuscado.getAdmin()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuarioBuscado);
            return new ModelAndView("redirect:/admin/panel");
        } else {
            model.put("error", "Usuario o clave incorrecta");
        }
        return new ModelAndView("login", model);
    }


    @RequestMapping(path = "/registrarme", method = RequestMethod.POST)
    public ModelAndView registrarme(@ModelAttribute("usuario") Usuario usuario) {
        ModelMap model = new ModelMap();
        try{
            servicioLogin.registrar(usuario);
        } catch (UsuarioExistente e){
            model.put("error", "El usuario ya existe");
            return new ModelAndView("redirect:/home", model);
        } catch (Exception e){
            model.put("error", "Error al registrar el nuevo usuario");
            return new ModelAndView("home", model);
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView homePrincipal() {
        return new ModelAndView("home");
    }

     @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/login");
    }

//    @RequestMapping(value = "/nuevoUsuario",method = RequestMethod.GET)
//    public ModelAndView nuevoUsuario() {
//        ModelMap model = new ModelMap();
//        Usuario usuario = new Usuario();
//        model.put("usuario", usuario);
//
//        // Obtener las localidades por partido
//        Map<String, List<String>> localidadesPorPartido = new HashMap<>();
//        for (PartidosDeBsAs partido : PartidosDeBsAs.values()) {
//            localidadesPorPartido.put(partido.name(), Localidades.getLocalidadPorPartido(partido));
//        }
//        model.put("localidadesPorPartido", localidadesPorPartido);

//        return new ModelAndView("nuevoUsuario", model);
//    }

@RequestMapping(value = "/nuevoUsuario", method = RequestMethod.GET)
public ModelAndView nuevoUsuario() {
    ModelMap model = new ModelMap();
    Usuario usuario = new Usuario();
      model.put("usuario", usuario);
//    model.put("partidos", PartidosDeBsAs.values());
//    model.put("localidadesPorPartido", obtenerLocalidadesPorPartido());

    return new ModelAndView("nuevoUsuario", model);
}
//
//    private Map<String, List<String>> obtenerLocalidadesPorPartido() {
//        return Arrays.stream(Localidades.values())
//                .collect(Collectors.toMap(
//                        l -> l.getPartido().name(),
//                        Localidades::getLocalidad
//                ));
//    }



}

