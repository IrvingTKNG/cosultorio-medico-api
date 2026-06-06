package com.example.consultorio_medico_api.utils.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorEnum  implements ErrorBs{
    USER_DUPLICATED("RN-001", "El usuario ya existe"),
    NULL_OBJECT("RN-002", "El objeto no existe");

    private final String codigo;
    private final String mensaje;

    @Override
    public String getCode() {
        return codigo;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
