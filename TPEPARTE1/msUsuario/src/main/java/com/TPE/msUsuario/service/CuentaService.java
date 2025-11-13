package com.TPE.msUsuario.service;

import com.TPE.msUsuario.model.Cuenta;
import com.TPE.msUsuario.repository.ICuentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService implements ICuentaService{

    @Autowired
    ICuentaRepository cuentaRepository;

    @Transactional
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    @Transactional
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Cuenta create(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Transactional
    public Cuenta update(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Transactional
    public void delete(Long id) {
        cuentaRepository.deleteById(id);
    }

    /****************************************************************/

    public Cuenta anularCuenta(Cuenta cuenta) { return this.update(cuenta); }
}
