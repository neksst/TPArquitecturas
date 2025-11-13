package com.TPE.msUsuario.service;

import com.TPE.msUsuario.client.MonopatinClient;
import com.TPE.msUsuario.dto.MonopatinDTO;
import com.TPE.msUsuario.model.Usuario;
import com.TPE.msUsuario.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private MonopatinClient  monopatinClient;


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
}
