package com.TPE.msUsuario.service;

import com.TPE.msUsuario.client.MonopatinClient;
import com.TPE.msUsuario.controller.CuentaController;
import com.TPE.msUsuario.dto.MonopatinDTO;
import com.TPE.msUsuario.model.Cuenta;
import com.TPE.msUsuario.model.Usuario;
import com.TPE.msUsuario.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private MonopatinClient  monopatinClient;

    @Autowired
    private ICuentaService cuentaService;


    @Transactional
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**********************************************************/

    public List<MonopatinDTO> obtenerMonopatinesCercanos(double latitud, double longitud, double radio){
        return monopatinClient.obtenerMonopatinesCercanos(latitud, longitud, radio);
    }

    @Override
    public Usuario addCuenta(Long usuarioID, Long cuentaID) {


        Usuario usuario = usuarioRepository.findById(usuarioID)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cuenta cuenta = cuentaService.findById(cuentaID);

        if (cuenta == null) {
            throw new RuntimeException("Cuenta no encontrada");
        }

        if (usuario.getCuentas() == null) {
            usuario.setCuentas(new ArrayList<>());
        }

        if (!usuario.getCuentas().contains(cuenta)) {
            usuario.getCuentas().add(cuenta);
        }

       return usuarioRepository.save(usuario);

    }


}
