package com.TPE.msUsuario.controller;

import com.TPE.msUsuario.dto.LoginRequest;
import com.TPE.msUsuario.dto.MonopatinDTO;
import com.TPE.msUsuario.dto.TokenResponse;
import com.TPE.msUsuario.model.Cuenta;
import com.TPE.msUsuario.model.Usuario;
import com.TPE.msUsuario.service.IUsuarioService;
import com.TPE.msUsuario.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCreated = usuarioService.create(usuario);
        if (usuarioCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarioCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.findById(id);

        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setCelular(usuario.getCelular());
        usuarioExistente.setEmail(usuario.getEmail());

        Usuario usuarioUpdated = usuarioService.update(usuarioExistente);

        return ResponseEntity.ok(usuarioUpdated);
    }

    /*******************************************************************/

    @GetMapping("/monopatin/cercanos")
    public ResponseEntity<List<MonopatinDTO>> getMonopatinCercanos(@RequestParam double latitud, @RequestParam double longitud, @RequestParam double radio){
        List<MonopatinDTO> monopatines = usuarioService.obtenerMonopatinesCercanos(latitud, longitud, radio);
        if (monopatines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(monopatines);
    }



    @PutMapping("/add/cuenta/{idUsuario}/{idCuenta}")
    public ResponseEntity<Usuario> addCuenta(@PathVariable Long idUsuario, @PathVariable Long idCuenta) {
        Usuario usuario = usuarioService.addCuenta(idUsuario, idCuenta);
        if (usuario == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        List<Usuario> usuarios = usuarioService.findAll();

        Usuario usuario = usuarios.stream()
                .filter(u -> u.getEmail().equals(request.getEmail()) &&
                        u.getPassword().equals(request.getPassword()))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email o contraseña incorrectos");
        }

        String token = jwtService.generateToken(usuario.getEmail());

        return ResponseEntity.ok(new TokenResponse(token));
    }

}
