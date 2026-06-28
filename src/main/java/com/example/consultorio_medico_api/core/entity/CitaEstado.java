package com.example.consultorio_medico_api.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CitaEstado {
    private Integer id;
    private String nombre;
}
