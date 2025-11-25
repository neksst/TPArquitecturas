package com.TPE.msUsuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatResponse {
    private boolean ok;
    private String message;
    private Object data;
}
