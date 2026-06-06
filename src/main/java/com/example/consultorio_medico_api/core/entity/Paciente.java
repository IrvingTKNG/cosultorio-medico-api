package com.example.consultorio_medico_api.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class Paciente {
    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numTelefono;
    private String numTelefonoAlterno;
    private String correo;
    private String numExpediente;
    private LocalDate fhNacimiento;
    private OffsetDateTime fhRegistro;

}
