package com.example.jwt.controller;

import com.example.jwt.model.entitats.Employee;
import com.example.jwt.model.serveis.ServeiEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class  ControladorEmployee{
    private final ServeiEmployee serveiEmployee;

    @GetMapping("/employee")
    public ResponseEntity<?> consultarEmployee() {
        List<Employee> res = serveiEmployee.llistarVideojocs();
        if (res != null) return ResponseEntity.ok(res);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/employee")
    public ResponseEntity<?> afegirEmployee(@RequestBody Employee v) {
        try {
            serveiEmployee.afegirVideojoc(v);
            return new ResponseEntity<Employee>(v, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> consultarUnVideojoc(@PathVariable long id) {
        Employee v = serveiEmployee.consultarPerId(id);
        if (v != null) {
            return ResponseEntity.ok(v);
        } else return ResponseEntity.notFound().build();
    }

    @PutMapping("/employee")
    public ResponseEntity<?> modificarVideojoc(@RequestBody Employee vmod){
        Employee res=serveiEmployee.modificarVideojoc(vmod);
        if(res!=null) return ResponseEntity.ok(res);
        else return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> eliminarVideojoc(@PathVariable long id){
        Employee res=serveiEmployee.eliminarVideojoc(id);
        if(res!=null){
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

}