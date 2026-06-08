package com.example.consultorio_medico_api.utils.error;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorEnum implements ErrorBs {
    USER_DUPLICATED("RN-001", "El usuario ya existe", HttpStatus.CONFLICT),       // 409
    NOT_FOUND("RN-002", "El objeto no existe", HttpStatus.NOT_FOUND);// 404

    private final String codigo;
    private final String mensaje;
    private final HttpStatus httpStatus; //Define el estatus

    @Override
    public String getCode() {
        return codigo;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
