package com.example.jwt.controller;


import com.example.jwt.model.entitats.Usuari;
import com.example.jwt.model.entitats.UsuariConsultaDTO;
import com.example.jwt.model.serveis.ServeiUsuari;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControladorRegistreLoginUsuaris {
    private final ServeiUsuari serveiUsuaris;

    /*
    {
    "username":"Oriol",
    "password":"password",
    "avatar":"http://imatge.com"
    }
    Afegeix id automàticament
     */
    @PostMapping("/usuaris")
    public ResponseEntity<?> nouUsuari(@RequestBody Usuari nouUsuari) {
        try {
            Usuari res = serveiUsuaris.crearNouUsuari(nouUsuari);
            UsuariConsultaDTO usu = new UsuariConsultaDTO(res.getUsername(), res.getAvatar(), res.getRol());
            return new ResponseEntity<UsuariConsultaDTO>(usu, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            //per si intentem afegir 2 usuaris amb el mateix username, saltarà excepció
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/usuaris")
    public ResponseEntity<?> llistarUsuarisDTO() {
        List<Usuari> res = serveiUsuaris.llistarUsuaris();
        List<UsuariConsultaDTO> aux = new ArrayList();
        for (Usuari usu : res) {
            aux.add(new UsuariConsultaDTO(usu.getUsername(), usu.getAvatar(), usu.getRol()));
        }
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(aux);
    }
}
