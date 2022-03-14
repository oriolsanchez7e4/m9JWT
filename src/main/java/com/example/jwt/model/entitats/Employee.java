package com.example.jwt.model.entitats;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@Entity
@RequiredArgsConstructor
public class Employee {
    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @GeneratedValue
    private String nom;
    private String plataforma;
    private double preu;
    private int horesJugades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
