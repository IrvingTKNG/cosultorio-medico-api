package com.example.consultorio_medico_api.utils.error;

import java.time.LocalDateTime;
import java.util.Map;

public record  ErrorResponseDto(
        int status,
        String error,
        String message,
        Map<String, String> errors, // Para guardar campos específicos que fallaron en la validación
        LocalDateTime timestamp
) {}