package com.example.consultorio_medico_api.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
public class Cita {
    private Integer id;
    private Integer idDoctor;
    private Integer idPaciente;
    private Integer idEstado;
    private String estado;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

}
