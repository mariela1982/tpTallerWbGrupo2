//package com.tallerwebi.dominio;
//
//
//import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//@Transactional
//public class ServicioUsuarioImpl implements ServicioUsuario {
//
//
//
//
//    RepositorioUsuario repositorioUsuario;
//    //Inyeccion de dependencia
//
//
//    @Autowired
//    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario) {
//        this.repositorioUsuario = repositorioUsuario;
//    }
//
//

//    @Override
//    public Usuario registrar(String email, String password) throws PasswordLongitudIncorrecta {
//       if(password.length() < 5){
//           throw new PasswordLongitudIncorrecta();
//       }
//
//       Usuario usuarioBuscado = repositorioUsuario.buscar(email);
//
//       if(usuarioBuscado != null){
//           return null;
//       }
//
//
//       Usuario usuario = new Usuario();
//       usuario.setEmail(email);
//       usuario.setPassword(password);
//       repositorioUsuario.guardar(usuario);
//
//
//
//
//        return new Usuario();
//    }
//}
