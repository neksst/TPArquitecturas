package com.TPE.msMonopatin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;
    private double latitud;
    private double longitud;

    @OneToMany(mappedBy = "parada")
    private List<Monopatin> listMonopatines;

}
