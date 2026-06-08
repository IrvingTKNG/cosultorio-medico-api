package com.example.consultorio_medico_api.utils.error;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ErrorMapper {
    @SuppressWarnings("unchecked")
    public static <T> ResponseEntity<T> mapToResponseEntity(ErrorBs error) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // Fallback por defecto

        // Si es una instancia del enum, se asigna el HttpStatus correspondiente
        if (error instanceof ErrorEnum errorEnum) {
            status = errorEnum.getHttpStatus();
        }

        ErrorResponseDto errorDto = new ErrorResponseDto(
                status.value(),
                status.getReasonPhrase(),
                error.getCode(),
                error.getMessage() // O error.getCode() si quieres mandarlo en otra propiedad
        );

        return (ResponseEntity<T>) ResponseEntity.status(status).body(errorDto);
    }
}
