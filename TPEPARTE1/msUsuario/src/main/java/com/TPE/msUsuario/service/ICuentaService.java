package com.TPE.msUsuario.service;

import com.TPE.msUsuario.model.Cuenta;

import java.util.List;

public interface ICuentaService {
    List<Cuenta> findAll();
    Cuenta findById(Long id);
    Cuenta create(Cuenta cuenta);
    Cuenta update(Cuenta cuenta);
    void delete(Long id);
    Cuenta anularCuenta(Cuenta cuenta);
}
