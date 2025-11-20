package com.TPE.msUsuario.service;

import com.TPE.msUsuario.dto.MonopatinDTO;
import com.TPE.msUsuario.model.Cuenta;
import com.TPE.msUsuario.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario create(Usuario usuario);
    Usuario update(Usuario usuario);
    void delete(Long id);
    List<MonopatinDTO> obtenerMonopatinesCercanos(double latitud, double longitud, double radio);
    Usuario addCuenta(Long usuarioID, Long cuentaID);

}
