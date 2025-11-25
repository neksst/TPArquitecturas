package com.TPE.msUsuario.dto;

import lombok.Data;

@Data
public class ChatRequest {
    private Long userId;
    private String prompt;
}
