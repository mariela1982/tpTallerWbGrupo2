package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.ServicioDt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorDt {
    private ServicioDt servicioDt;

    @Autowired
    public ControladorDt(ServicioDt servicioDt) {
        this.servicioDt = servicioDt;
    }

    @GetMapping("/directorTecnico")
    public ModelAndView panelAdmin(){
        return new ModelAndView();
    }


}
