package com.TPE.msUsuario.service;

import com.TPE.msUsuario.client.MonopatinClient;
import com.TPE.msUsuario.dto.ChatResponse;
import com.TPE.msUsuario.dto.LlmActionResponse;
import com.TPE.msUsuario.dto.MonopatinDTO;
import com.TPE.msUsuario.model.Cuenta;
import com.TPE.msUsuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    @Autowired
    private LlmClient llmClient;

    @Autowired
    private IUsuarioService usuarioService; // para obtener user y verificar premium

    @Autowired
    private MonopatinClient monopatinClient;

    @Autowired
    private ICuentaService cuentaService;

    public ChatResponse procesarPregunta(String prompt, Long userId) {
        // 1) Validar usuario y permisos
        Usuario usuario = usuarioService.findById(userId);
        if (usuario == null) {
            return new ChatResponse(false, "Usuario no encontrado", null);
        }
        if (!usuario.isEsPremium()) {
            return new ChatResponse(false, "Servicio disponible solo para usuarios premium", null);
        }

        // 2) Interpretar con LLM
        LlmActionResponse accionResp = llmClient.interpretar(prompt);
        String accion = accionResp.getAccion();
        Map<String, Object> params = accionResp.getParametros();

        try {
            switch (accion) {

                case "monopatines_cercanos":
                    double lat = ((Number) params.get("lat")).doubleValue();
                    double lon = ((Number) params.get("lon")).doubleValue();
                    double radio = ((Number) params.get("radio")).doubleValue();
                    List<MonopatinDTO> list = monopatinClient.obtenerMonopatinesCercanos(lat, lon, radio);
                    return new ChatResponse(true, "Monopatines encontrados: " + list.size(), list);

                case "anular_cuenta":
                    Long cuentaId = params.containsKey("cuentaId")
                            ? ((Number) params.get("cuentaId")).longValue()
                            : null;

                    if (cuentaId == null) {
                        return new ChatResponse(false, "Debes indicar el ID de la cuenta a anular.", null);
                    }

                    Cuenta cuenta = cuentaService.findById(cuentaId);
                    if (cuenta == null) {
                        return new ChatResponse(false, "Cuenta no encontrada.", null);
                    }

                    cuenta.setActiva(false);
                    Cuenta anulada = cuentaService.anularCuenta(cuenta);

                    return new ChatResponse(true, "Cuenta anulada correctamente.", anulada);
                default:
                    return new ChatResponse(false, "No pude interpretar la acción solicitada.", null);
            }
        } catch (Exception e) {
            return new ChatResponse(false, "Error ejecutando la acción: " + e.getMessage(), null);
        }
    }
}
