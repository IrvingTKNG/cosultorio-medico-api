package com.example.consultorio_medico_api.core.entity;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDetalles {
    private String medicamento;
    private String dosis;
}
