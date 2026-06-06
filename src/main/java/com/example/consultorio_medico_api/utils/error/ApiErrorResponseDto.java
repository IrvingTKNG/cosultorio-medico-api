package com.example.consultorio_medico_api.utils.error;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ApiErrorResponseDto {
    private String code;
    private String message;
    private LocalDateTime timestamp;
}
