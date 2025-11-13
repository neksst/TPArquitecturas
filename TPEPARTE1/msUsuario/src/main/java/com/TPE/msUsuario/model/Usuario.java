package com.TPE.msUsuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String celular;
    private String email;

    private List<String> listIdCuentas;

    @ManyToMany
    private List<Cuenta> cuentas;

}
