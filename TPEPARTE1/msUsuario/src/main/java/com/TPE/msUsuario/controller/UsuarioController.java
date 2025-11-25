package com.TPE.msUsuario.controller;

import com.TPE.msUsuario.dto.*;
import com.TPE.msUsuario.model.Cuenta;
import com.TPE.msUsuario.model.Usuario;
import com.TPE.msUsuario.service.ChatService;
import com.TPE.msUsuario.service.IUsuarioService;
import com.TPE.msUsuario.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuario", description = "Endpoints para la gestión de usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ChatService chatService;

    // ============================
    // 1. Obtener todos los usuarios
    // ============================
    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Devuelve la lista completa de usuarios registrados en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado con éxito"),
            @ApiResponse(responseCode = "204", description = "No hay usuarios registrados")
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // ============================
    // 2. Obtener usuario por ID
    // ============================
    @Operation(
            summary = "Obtener usuario por ID",
            description = "Devuelve los datos de un usuario específico según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // ============================
    // 3. Crear usuario
    // ============================
    @Operation(
            summary = "Crear usuario",
            description = "Registra un nuevo usuario en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el usuario")
    })
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCreated = usuarioService.create(usuario);
        if (usuarioCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarioCreated);
    }

    // ============================
    // 4. Eliminar usuario
    // ============================
    @Operation(
            summary = "Eliminar usuario",
            description = "Elimina un usuario según su ID."
    )
    @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============================
    // 5. Actualizar usuario
    // ============================
    @Operation(
            summary = "Actualizar usuario",
            description = "Actualiza los datos de un usuario existente según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
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

    // ============================
    // 6. Obtener monopatines cercanos
    // ============================
    @Operation(
            summary = "Obtener monopatines cercanos",
            description = "Devuelve los monopatines dentro de un radio específico alrededor de la ubicación indicada."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron monopatines cercanos")
    })
    @GetMapping("/monopatin/cercanos")
    public ResponseEntity<List<MonopatinDTO>> getMonopatinCercanos(
            @RequestParam double latitud,
            @RequestParam double longitud,
            @RequestParam double radio) {

        List<MonopatinDTO> monopatines = usuarioService.obtenerMonopatinesCercanos(latitud, longitud, radio);
        if (monopatines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(monopatines);
    }

    // ============================
    // 7. Asociar cuenta a usuario
    // ============================
    @Operation(
            summary = "Agregar cuenta a usuario",
            description = "Asocia una cuenta existente a un usuario específico."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cuenta asociada correctamente"),
            @ApiResponse(responseCode = "204", description = "Usuario o cuenta no encontrada")
    })
    @PutMapping("/add/cuenta/{idUsuario}/{idCuenta}")
    public ResponseEntity<Usuario> addCuenta(@PathVariable Long idUsuario, @PathVariable Long idCuenta) {
        Usuario usuario = usuarioService.addCuenta(idUsuario, idCuenta);
        if (usuario == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // ============================
    // 8. Login de usuario
    // ============================
    @Operation(
            summary = "Login de usuario",
            description = "Autentica un usuario y devuelve un token JWT si las credenciales son correctas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login exitoso, token generado"),
            @ApiResponse(responseCode = "401", description = "Email o contraseña incorrectos")
    })
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

    @Operation(
            summary = "Realiza una consulta en lenguaje natural (Chat LLM) para usuarios premium",
            description = "Este endpoint recibe una pregunta del usuario y la procesa utilizando un modelo LLM (Grok u otro). "
                    + "Requiere enviar el ID del usuario y el prompt a consultar. "
                    + "Devuelve una respuesta generada por IA junto con el estado de la operación."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta procesada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ChatResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error al procesar la consulta (usuario no encontrado, usuario no premium, etc.)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ChatResponse.class)
                    )
            )
    })
    @PostMapping("/chatPregunta")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        ChatResponse resp = chatService.procesarPregunta(request.getPrompt(), request.getUserId());
        if (!resp.isOk()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
        return ResponseEntity.ok(resp);
    }

}