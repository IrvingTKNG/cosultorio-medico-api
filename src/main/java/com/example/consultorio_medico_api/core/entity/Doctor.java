package com.example.consultorio_medico_api.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Builder
@Getter
@Setter
public class Doctor {
    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numTelefono;
    private String numTelefonoAlterno;
    private String correo;
    private String cedula;
    private String especialidad;
    private LocalTime entrada;
    private LocalTime salida;
}
