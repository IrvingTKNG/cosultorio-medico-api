package com.example.consultorio_medico_api.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class Receta {
    private Integer id;
    private Integer idConsulta;
    private LocalDate fecha;
    private String indicaciones;
    private List<RecetaDetalles> detalles;
}
