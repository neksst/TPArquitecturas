package com.TPE.msUsuario.service;

import com.TPE.msUsuario.client.GroqClient;
import com.TPE.msUsuario.dto.LlmActionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LlmClient {

    private final GroqClient groqClient;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public LlmClient(GroqClient groqClient) {
        this.groqClient = groqClient;
    }

    public LlmActionResponse interpretar(String userPrompt) {
        String promptTemplate = """
            YOU ARE: Un parser que convierte preguntas en acciones estructuradas JSON.

            INSTRUCCIONES:
            - Basate únicamente en la intención del usuario.
            - Respondé ÚNICAMENTE con un JSON EXACTO sin texto adicional ni markdown.
            - El JSON debe tener:
              {
                "accion": "<accion>",
                "parametros": { ... }
              }
            - Acciones válidas:
              - monopatines_cercanos -> parametros: { "lat": number, "lon": number, "radio": number }
              - anular_cuenta -> parametros: { "cuentaId": number }
            - Si no entendés la intención respondé:
              { "accion": "unknown", "parametros": {} }

            USER: %s
            """.formatted(userPrompt);

        String respuesta = groqClient.preguntar(promptTemplate);

        try {
            // Normalizamos la respuesta por si el LLM agrega espacios
            String json = respuesta.trim();
            return mapper.readValue(json, LlmActionResponse.class);
        } catch (Exception e) {
            // Loguear y devolver unknown
            LoggerFactory.getLogger(LlmClient.class).warn("Error parseando respuesta LLM: {}", e.getMessage());
            return new LlmActionResponse("unknown", Collections.emptyMap());
        }
    }
}