package com.example.consultorio_medico_api.utils.error;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDto(
        int status,
        String error,
        String code,
        String message,
        Map<String, String> errors, // Para guardar campos específicos que fallaron en la validación
        LocalDateTime timestamp
) {
    // Constructor para errores comunes
    public ErrorResponseDto(int status, String error, String code, String message) {
        this(status, error, code, message, null, LocalDateTime.now());
    }

    // Constructor para mapa de errores específicos
    public ErrorResponseDto(int status, String error, String code, String message, Map<String, String> errors) {
        this(status, error, code, message, errors, LocalDateTime.now());
    }
}