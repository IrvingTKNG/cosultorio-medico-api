package com.example.consultorio_medico_api.core.entity;

import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
    private Integer id;
    private  Integer idDoctor;
    private  Integer idCita;
    private LocalDate fecha;
    private String observaciones;
    private String diagnostico;
    private String motivo;
}
