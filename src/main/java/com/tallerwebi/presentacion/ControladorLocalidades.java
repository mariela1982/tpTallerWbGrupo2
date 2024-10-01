package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.enums.Localidades;
import com.tallerwebi.dominio.enums.PartidosDeBsAs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spring")
public class ControladorLocalidades {

        @GetMapping("/api/localidades")
        public ResponseEntity<List<String>> obtenerLocalidades(@RequestParam("partido") PartidosDeBsAs partido) {
            List<String> localidades = Localidades.getLocalidadPorPartido(partido);
            return ResponseEntity.ok(localidades); // Devolver la lista de localidades como JSON
        }

}
