package com.example.consultorio_medico_api.utils.error;

import lombok.Builder;
import lombok.Getter;

/***
 * Extrae los errores de las reglas de negocio
 */
@Getter
public class BusinessException extends RuntimeException {

    private final String code;

    @Builder
    public BusinessException(ErrorBs errorBs) {
        super(errorBs.getMessage());
        this.code = errorBs.getCode();
    }
}
