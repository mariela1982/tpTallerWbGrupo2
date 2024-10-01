package com.tallerwebi.presentacion;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dt")
public class ControladorDt {



    @GetMapping("/panelDt")
    public ModelAndView dtPanel(){return new ModelAndView("dt/panel");}


    @GetMapping("/jugadoresDt")
    public ModelAndView jugadoresDt(){return new ModelAndView("dt/jugadoresDt");}
}

